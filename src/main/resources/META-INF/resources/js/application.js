/*** Configuration ***/
let serviceUrl = '/coviddata';

const county = getUrlParameter('county');
if (county) {
    serviceUrl = serviceUrl + '?county=' + county;
}

$('.spinner').html('<div class="spinner-border spinner-border-sm" role="status"><span class="visually-hidden">Lade Covid-Statistiken...</span></div>');

/*** Imprint ***/
console.log(document.domain);
if (document.domain !== 'neinhorn.codefoundry.de') {
    $('#imprint').remove();
}

/*** Functions ***/

function formatFloat(float) {
    console.log(float);
    return Number(float).toFixed(2).replace('.', ',');
}

function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return typeof sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
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
        const districtWeekIncidence = formatFloat(result.districtStatisticsData.currentWeekIncidence);

        $('#firstDosePercentage').text(firstDosePercentage);
        $('#secondDosePercentage').text(secondDosePercentage);
        $('#deltaCases').text(result.statisticsData.deltaCases);
        $('#weekIncidence').text(weekIncidence);
        $('#districtName').text(result.districtStatisticsData.name);
        $('#districtCounty').text(result.districtStatisticsData.county);
        $('#districtDeltaCases').text(result.districtStatisticsData.deltaCases);
        $('#districtWeekIncidence').text(districtWeekIncidence);
    },

    error: function (result) {
        $('.spinner').html('<i class="bi bi-exclamation-triangle-fill"><span class="visually-hidden">Beim Abruf von Covid-Statistiken ist ein Fehler aufgetreten.</span></i>');
    }
});
