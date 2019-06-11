
var jsonRequestpays = function(){

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
            var option;
            for (var i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].name;
                option.value = data[i].alpha3Code;
                var flagicon = document.createElement("i");

                option.appendChild()
                option.data
                dropdown.add(option);
            }
        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}
window.onload = function(){
    jsonRequestpays();
}

