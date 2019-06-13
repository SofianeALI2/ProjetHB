var plotFunction = function(langProp){
    var ctx = document.getElementById("chartLang");
    var ndata = langProp.length;
    var hasUnspecified = false ;
    var isUnspecified = langProp[ndata - 1].languageName;
    if(isUnspecified === "unspecified"){
        ndata = ndata-1;
        hasUnspecified = true;
    }
    var labelArray = [];
    var dataArray = [];
    for(var i = 0; i < langProp.length ; i++){
        dataArray.push(langProp[i].languageProp);
        labelArray.push(langProp[i].languageName);
    }
    console.dir(dataArray);
    var seq = palette("rainbow" , ndata);
    var colorList = seq.map(function(hex){return '#'+hex;});
    if(hasUnspecified){
        colorList.push("#000000");
    }

    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels:labelArray ,
            datasets: [{
                data: dataArray,
                //backgroundColor: seq.map(function(hex){return '#'+hex;}),
                backgroundColor :"rgba(255,0,0,0.5)" ,
                borderWidth: 1
            }]
        },
        options: {
            legend: {
                display: false
            },
            title:{
                display: true,
                text: 'Language distribution in population'
            },
            scales: {
                yAxes: [{
                    scaleLabel: {
                        display: true,
                        labelString: '% of people speaking language'
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}

var ageStructures = function(country){
    var ctx = document.getElementById("chartAgeStructure");
    var ndata = 5;
    var seq = palette("rainbow" , ndata);
    var colorList = seq.map(function(hex){return '#'+hex;});

    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data : {
            datasets: [{
                data: [country.zero14,country.fifteen24,country.twentyFive54,country.fiftyFive64 , country.over65],
                backgroundColor : colorList
            }],

            // These labels appear in the legend and in the tooltips when hovering different arcs
            labels:['0-14', '15-24', '25-54', '54-64', '65+']


        },

        options:{title:{
                display: true,
                text: '% of people in age braket'
            }}

    });
}

/*
var plotLanguages = function(langProp){
    var ctx = document.getElementById("chartLang");
    var ndata = langProp.length;
    var hasUnspecified = false ;
    var isUnspecified = langProp[ndata - 1].languageName;
    if(isUnspecified === "unspecified"){
        ndata = ndata-1;
        hasUnspecified = true;
    }
    var labelArray = [];
    var dataArray = [];
    for(var i = 0; i < langProp.length ; i++){
        dataArray.push(langProp[i].languageProp);
        labelArray.push(langProp[i].languageName);
    }
    console.dir(dataArray);
var seq = palette("rainbow" , ndata);
var colorList = seq.map(function(hex){return '#'+hex;});
if(hasUnspecified){
    colorList.push("#000000");
}

console.dir(colorList);
var myPieChart = new Chart(ctx, {
    type: 'pie',
    data : {
        datasets: [{
            data: dataArray,
            backgroundColor : colorList
        }],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels:labelArray


    },

    options:{title:{
            display: true,
            text: '% De chaque langage dans la population'
        }}

    });
}*/
