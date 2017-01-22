jQuery(document).ready(function ($) {
    var to;
    var from;
    var save;

    var firstCommitFrom;
    var firstCommitTo;

    initParams();

    function initDate() {
        now = new Date();
        now.setDate(1);
        month = now.getMonth();

        if (month >= 0 && month <= 2) {
            from = new Date(now.setMonth(0));
            now.setDate(31);
            to = new Date(now.setMonth(2));
        }

        else if (month >= 3 && month <= 5) {
            from = new Date(now.setMonth(3));
            now.setDate(30);
            to = new Date(now.setMonth(5));
        }

        else if (month >= 6 && month <= 8) {
            from = new Date(now.setMonth(6));
            now.setDate(30);
            to = new Date(now.setMonth(8));
        }

        else if (month >= 9 && month <= 11) {
            from = new Date(now.setMonth(9));
            now.setDate(31);
            to = new Date(now.setMonth(11));
        }
        $("#fromDate").mask("99.99.99", {placeholder: "dd.mm.yy"});
        $("#toDate").mask("99.99.99", {placeholder: "dd.mm.yy"});
        $("#fromDate").val(dateToString(from));
        $("#toDate").val(dateToString(to));
    }

    function dateToString(date) {
        str = date.getDate() + '.' + (date.getMonth() + 1) + '.' + (date.getFullYear() - 2000);
        return str;
    }

    function stringToDate(str) {
        now = new Date();
        var nowYear = now.getFullYear();

        var pattern = new RegExp("^([0-9]{1,2}\\.){2}[0-9]{1,2}$");
        var isOk = pattern.test(str);
        if (isOk == false)
            return null;

        afterDayPos = str.indexOf(".");
        dayStr = str.substring(0, afterDayPos);
        str = str.substring(afterDayPos + 1);

        afterMonthPos = str.indexOf(".");
        monthStr = str.substring(0, afterMonthPos);
        str = str.substring(afterMonthPos + 1);

        yearStr = str;

        var day = parseInt(dayStr);
        var month = parseInt(monthStr) - 1;
        var year = parseInt(yearStr) + 2000;

        var lastDayOfMonth = getDaysInMonth(month + 1, year);

        if (day < 1 || day > lastDayOfMonth)
            return null;
        else if (month < 0 || month > 11)
            return null;
        else if (year < 2000 || year > nowYear)
            return null;

        var dateOut = new Date(year, month, day);
        if (isNaN(dateOut.getTime()))
            return null;

        return dateOut;
    }

    function getDaysInMonth(month, year) {
        // Here January is 1 based
        //Day 0 is the last day in the previous month
        return new Date(year, month, 0).getDate();
    }

    function initParams() {
        firstCommitFrom = true;
        firstCommitTo = true;
        initDate();
        updateUrls();
    }

    function updateUrls() {
        refParams = "?from=" + from.getDate() + "+"
            + (from.getMonth() + 1) + "+" + from.getFullYear() + "&to="
            + to.getDate() + "+" + (to.getMonth() + 1) + "+" + to.getFullYear();

        q1Ref = "/reports/xls/report-1.xls";
        q3Ref = "/reports/xls/report-3.xls";
        q4Ref = "/reports/xls/report-2-4.xls";
        q5Ref = "/reports/xls/report-5.xls";
        q6Ref = "/reports/xls/report-6.xls";
        q7Ref = "/reports/xls/report-7.xls";

        q1Link = q1Ref + refParams;
        q3Link = q3Ref + refParams;
        q4Link = q4Ref + refParams;
        q5Link = q5Ref + refParams;
        q6Link = q6Ref + refParams;
        q7Link = q7Ref + refParams;

        $("#refQuery1").attr("href", q1Link);
        $("#refQuery3").attr("href", q3Link);
        $("#refQuery4").attr("href", q4Link);
        $("#refQuery5").attr("href", q5Link);
        $("#refQuery6").attr("href", q6Link);
        $("#refQuery7").attr("href", q7Link);
    }

    function isFillDateFieldsOk(fromDate, toDate) {
        if (fromDate == null || toDate == null)
            return false;
        if (fromDate.getTime() < toDate.getTime())
            return true;
        return false;
    }

    $('#fromDate').blur(function () {
        save = from;

        fromDate = stringToDate($('#fromDate').val());
        toDate = stringToDate($('#toDate').val());
        if (isFillDateFieldsOk(fromDate, toDate) == true) {
            from = fromDate;
            updateUrls();
        }
        else {
            alert('Error on date From field!');
            $("#fromDate").val(dateToString(save));
        }
    });

    $('#toDate').blur(function () {
        save = to;

        fromDate = stringToDate($('#fromDate').val());
        toDate = stringToDate($('#toDate').val());
        if (isFillDateFieldsOk(fromDate, toDate) == true) {
            to = toDate;
            updateUrls();
        }
        else {
            alert('Error on date To field!');
            $("#toDate").val(dateToString(save));
        }
    });
});