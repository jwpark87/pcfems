;
AotChartjs = {
    addData: function (chart, label, newData) {
        chart.data.labels.shift();
        chart.data.labels.push(label);
        chart.data.datasets.forEach(function (dataset, index) {
            dataset.data.push(newData[index]); // add new data at end
            dataset.data.shift();
            // dataset.data.splice(0, 1); // remove first data point
        });
        chart.update();
    },
    removeData: function (chart) {
        chart.data.labels.pop();
        $.each(chart.data.datasets, function (index, value) {
            value.data.pop();
        });
        chart.update();
    },
    moveChart: function (chart, label, newData) {
        // AotChartjs.removeData(chart);
        AotChartjs.addData(chart, label, newData);
        // chart.data.labels.push(label); // add new label at end
        // chart.data.labels.splice(0, 1); // remove first label
        //
        // chart.data.datasets.forEach(function(dataset, index) {
        // dataset.data.push(newData[index]); // add new data at end
        // dataset.data.splice(0, 1); // remove first data point
        // });
        //
        // chart.update();
    }
};
