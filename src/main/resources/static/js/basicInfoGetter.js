
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

var jsonRequestpays = function(){

    let dropdown = document.getElementById('country');
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre pays--';

    dropdown.add(defaultOption);
    dropdown.selectedIndex = 5;

    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/chargePays";
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('('+this.responseText+')');
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].name;
                option.value = data[i].alpha3Code;
                dropdown.add(option);
            }
          }
        };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}
window.onload = jsonRequestpays;

