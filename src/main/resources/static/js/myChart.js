/*
 The argument of decodeHtml (chartData) coming from the HOME.html
 we placed it in a script tag under the canvas
*/
var ChartDataStr = decodeHtml(chartData);
/*
 Now, converting the String that arrived from decodeHtml
 into a Javascript Object
 JSON.parse - will parse a String into a Json
 Now we can traverse this array and insert items into the Chart
*/
var chartJsonArray = JSON.parse(ChartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData   = [];

for (var i=0; i < arrayLength; i++) {
    numericData[i] = chartJsonArray[i].value;
    labelData[i]   = chartJsonArray[i].label;
}

// for the pie Chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'Projects Management Webapp',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
        title: {
            display: true,
            text: 'Project Statuses'
        }
    }
});

/*
 The function will accept an unformatted Json like:
 [{&quot;label&quot;:&quot;COMPLETED&quot;,&quot;value&quot;:1},{&quot;label&quot;:&quot;INPROGRESS&quot;,&quot;value&quot;:2},{&quot;label&quot;:&quot;NOTSTARTED&quot;,&quot;value&quot;:1}]

 The String return value from the function will be like:
 "[{"label": "COMPLETED", "value": 1},{"label": "INPROGRESS", "value": 2},{"label": "NOTSTARTED", "value": 1}]"
*/
function decodeHtml (html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}