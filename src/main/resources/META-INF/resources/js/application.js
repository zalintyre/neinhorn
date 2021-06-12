const serviceUrl = '/coviddata';

$.ajax({
    url: serviceUrl,
    contentType: 'application/json',
    dataType: 'json',

    success: function (result) {
        const firstDoseQuote = result.vaccinationsData.firstDoseQuote;
        const firstDosePercentage = (firstDoseQuote * 100) + '%';

        const secondDoseQuote = result.vaccinationsData.secondDoseQuote;
        const secondDosePercentage = (secondDoseQuote * 100) + '%';

        const weekIncidence = Number(result.statisticsData.currentWeekIncidence).toFixed(2).replace('.', ',');

        $('#firstDosePercentage').text(firstDosePercentage);
        $('#secondDosePercentage').text(secondDosePercentage);
        $('#deltaCases').text(result.statisticsData.deltaCases);
        $('#weekIncidence').text(weekIncidence);
    }
})
