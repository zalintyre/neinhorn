const serviceUrl = '/coviddata';

$('.spinner').html('<div class="spinner-border spinner-border-sm" role="status"><span class="visually-hidden">Lade Covid-Statistiken...</span></div>');

function formatFloat(float) {
    console.log(float);
    return Number(float).toFixed(2).replace('.', ',');
}

$.ajax({
    url: serviceUrl,
    contentType: 'application/json',
    dataType: 'json',

    success: function (result) {
        const firstDoseQuote = formatFloat(result.vaccinationsData.firstDoseQuote * 100);
        const firstDosePercentage = firstDoseQuote + '%';

        const secondDoseQuote = formatFloat(result.vaccinationsData.secondDoseQuote * 100);
        const secondDosePercentage = secondDoseQuote + '%';

        const weekIncidence = formatFloat(result.statisticsData.currentWeekIncidence);

        $('#firstDosePercentage').text(firstDosePercentage);
        $('#secondDosePercentage').text(secondDosePercentage);
        $('#deltaCases').text(result.statisticsData.deltaCases);
        $('#weekIncidence').text(weekIncidence);
    },

    error: function (result) {
        $('.spinner').html('<i class="bi bi-exclamation-triangle-fill"><span class="visually-hidden">Beim Abruf von Covid-Statistiken ist ein Fehler aufgetreten.</span></i>');
    }
});
