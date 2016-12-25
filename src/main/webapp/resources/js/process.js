jQuery(document).ready(function($){
        var to;
        var from;

        var firstCommitFrom;
        var firstCommitTo;

        initParams();

        function initDate() {
            now = new Date();
            now.setDate(1);
            month = now.getMonth();

            if (month >= 0 && month <= 2){
                from = new Date(now.setMonth(0));
                now.setDate(31);
                to = new Date(now.setMonth(2));
            }

            else if (month >= 3 && month <= 5){
                from = new Date(now.setMonth(3));
                now.setDate(30);
                to = new Date(now.setMonth(5));
            }

            else if (month >= 6 && month <= 8){
                from = new Date(now.setMonth(6));
                now.setDate(30);
                to = new Date(now.setMonth(8));
            }

            else if (month >= 9 && month <= 11){
                from = new Date(now.setMonth(9));
                now.setDate(31);
                to = new Date(now.setMonth(11));
            }
            $("#fromDate").mask("99.99.9999", {placeholder: "дд.мм.гггг" });
            $("#toDate").mask("99.99.9999", {placeholder: "дд.мм.гггг" });
            $("#fromDate").val(dateToString(from));
            $("#toDate").val(dateToString(to));
        }

        function dateToString(date) {
            return date.getDate()+'.'+(date.getMonth()+1)+'.'+date.getFullYear();
        }

        function stringToDate(str) {
           now = new Date();
           var nowYear = now.getFullYear();

           dayStr = str[0]+str[1];
           monthStr = str[3]+str[4];
           yearStr = str[6]+str[7]+str[8]+str[9];

           var day = parseInt(dayStr);
           var month = parseInt(monthStr)-1;
           var year = parseInt(yearStr);

           var lastDayOfMonth = getDaysInMonth(month+1, year);

           if(day < 1 || day > lastDayOfMonth)
               return null;
           else if(month < 1 || month > 11)
               return null;
           else if(year < 2000 || year > nowYear)
               return null;

           var dateOut = new Date(year, month, day);
           if (isNaN(dateOut.getTime()))
                return null;

           return dateOut;
       }

        function getDaysInMonth(month,year) {
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
                            + (from.getMonth()+1) + "+" + from.getFullYear() + "&to="
                             + to.getDate() + "+" + (to.getMonth()+1) + "+" + to.getFullYear();

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

        $('#fromDate').focusout(function() {
           date = stringToDate($('#fromDate').val());
           if(date == null)
                $('#fromDate').focus();
           else {
                from = date;
                updateUrls();
           }
        });

        $('#toDate').focusout(function() {
           dateTo = stringToDate($('#toDate').val());
           dateFrom = stringToDate($('#fromDate').val());
           if(dateTo == null)
                $('#toDate').focus();
           else if(dateFrom != null && dateTo.getTime() < dateFrom.getTime())
                $('#toDate').focus();
           else {
                to = dateTo;
                updateUrls();
           }
        });
});