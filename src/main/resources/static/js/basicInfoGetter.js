var introTextSetter = function(introText){
    var textField = document.getElementById("introText");
    textField.innerText = introText;
}

var onImgLoad = function(countryFlag){
    var imgField = document.getElementById("imgFlagId");
    imgField.onload = function(e){
        imgField.src = countryFlag;
    };

    if(imgField.complete) {
        imgField.onload();
    }
}

var onMapLoad = function(country){
    var imgField = document.getElementById("imgMapId");
    imgField.onload = function(e){
        imgField.src = "https://www.cia.gov/library/publications/the-world-factbook/attachments/maps/"+country.ciaCode.toUpperCase()+"-map.gif";
    };

    if(imgField.complete) {
        imgField.onload();
    }
}

var setTableElement = function(country){
    document.getElementById('nameTable').innerHTML = country.name;
    document.getElementById('altSpellTable').innerHTML = country.altspelling;
    document.getElementById('capitalTable').innerHTML = country.capital;
    document.getElementById('demonymTable').innerHTML = country.demonym;
    document.getElementById('langageTable').innerHTML = country.langage;
    document.getElementById('regionTable').innerHTML = country.region;
    document.getElementById('subregionTable').innerHTML = country.subregion;
    document.getElementById('populationTable').innerHTML = country.population;
    document.getElementById('bordersTable').innerHTML = country.borders;
    document.getElementById('currencyTable').innerHTML = country.currencies;


}


var jsonRequest = function(code3Alpha){
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/displayBasicInfo/"+code3Alpha;
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var country = eval('('+this.responseText+')');
            console.dir(country);
            introTextSetter(country.introText);
            onImgLoad(country.flag);
            setTableElement(country);
            onMapLoad(country);

        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}

var getAlpha3Code = function(){

}

window.onload = function(){
    var code3Alpha = document.getElementById("AlphaCode").innerHTML;
    console.log("La valeur est " + code3Alpha);
    jsonRequest(code3Alpha);
}

