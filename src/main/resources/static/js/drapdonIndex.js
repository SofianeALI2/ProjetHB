
function jsonRequestpays(){

    var dropdown = document.getElementById('country');
    dropdown.length = 0;

    var defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre pays--';

    dropdown.add(defaultOption);
    dropdown.selectedIndex = 5;

    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/chargePays";
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('('+this.responseText+')');
            var option2;
            for (var i = 0; i < data.length; i++) {
                option2 = document.createElement('option');
                option2.text = data[i].name;
                option2.value = data[i].alpha3Code;
                dropdown.add(option2);
            }
        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}


function jsonRequestregion(){
    var dropdownR = document.getElementById('region');
    dropdownR.length = 0;

    var defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre region--';

    dropdownR.add(defaultOption);
    dropdownR.selectedIndex =0;

    var httpRequest = new XMLHttpRequest();
    var url = "http://localhost:9000/chargeRegion";
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('('+this.responseText+')');
            var option;
            for (var i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].region;
                dropdownR.add(option);
            }
        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();

}


function jsonRequestSubRegion(){
    var dropdown = document.getElementById('subregion');
    dropdown.length =0;
    var region=document.getElementById('region');

    var defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre subregion--';

    dropdown.add(defaultOption);
    dropdown.selectedIndex =0;
    if(region.value!="--Choisissez votre region--") {
        var httpRequest = new XMLHttpRequest();
        var url = "http://localhost:9000/chargeSubRegion/"+region.value;
        httpRequest.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                var data = eval('(' + this.responseText + ')');
                var option;
                for (var i = 0; i < data.length; i++) {
                    option = document.createElement('option');
                    option.text = data[i].subregion;
                    dropdown.add(option);
                }
            }
        };
        httpRequest.open("GET", url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
        httpRequest.send();

        ////////////////////
        jsonRequestPaysFromRegion(region.value);
}else{
        jsonRequestpays();
    }
}

function jsonRequestPaysFromRegion(region){

    var dropdown = document.getElementById('country');
    dropdown.length =0;

    console.log(region);

    var defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre pays--';

    dropdown.add(defaultOption);
    dropdown.selectedIndex =0;

    var httpRequestpays = new XMLHttpRequest();
    var url = "http://localhost:9000/chargePaysFromRegion/"+region;
    httpRequestpays.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');
            var option;
            for (var i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].name;
                option.value=data[i].alpha3Code;
                dropdown.add(option);
            }
        }
    };
    httpRequestpays.open("GET", url, true);
    httpRequestpays.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
    httpRequestpays.send();
}

function jsonRequestPaysFromSubegion(){
    var region=document.getElementById('region');
    var subregion = document.getElementById('subregion');
    var dropdown = document.getElementById('country');
    dropdown.length =0;

    console.log(region.value);
    console.log(subregion.value);
    var defaultOption = document.createElement('option');
    defaultOption.text = '--Choisissez votre pays--';

    dropdown.add(defaultOption);
    dropdown.selectedIndex =0;
if(subregion.value!="--Choisissez votre subregion--"){
    var httpRequestpays = new XMLHttpRequest();
    var url = "http://localhost:9000/chargePaysFromSubRegion/"+subregion.value;
    httpRequestpays.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var data = eval('(' + this.responseText + ')');
            var option;
            for (var i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].name;
                option.value=data[i].alpha3Code;
                dropdown.add(option);
            }
        }
    };
    httpRequestpays.open("GET", url, true);
    httpRequestpays.setRequestHeader('Content-Type', 'application/json;charset-UTF-8');
    httpRequestpays.send();
}else{
    jsonRequestPaysFromRegion(region.value);
}
if((subregion.value==="--Choisissez votre subregion--")&&(region.value==="--Choisissez votre region--")){
    jsonRequestpays();
}

}

