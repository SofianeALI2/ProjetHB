
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
        imgField.src = "https://www.cia.gov/library/publications/the-world-factbook/attachments/maps/"+country.alpha2Code+"-map.gif";
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

}


var jsonRequest = function(){
    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/displayBasicInfo/FRA";
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var country = eval('('+this.responseText+')');
            console.dir(country);
            onImgLoad(country.flag);
            setTableElement(country);
            onMapLoad(country);

        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}
window.onload = jsonRequest;
