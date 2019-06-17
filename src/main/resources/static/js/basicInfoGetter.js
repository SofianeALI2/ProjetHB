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

   // ---------------- For poulation tableau ---------------------------
    document.getElementById('popTable').innerHTML = country.population
    document.getElementById('medianAgeTable').innerHTML = country.medianAge;
    document.getElementById('birthRateTable').innerHTML = country.birthRate;
    document.getElementById('urbanPopTable').innerHTML = country.urbanPop;
    document.getElementById('deathRateTable').innerHTML = country.deathRate;
    document.getElementById('sexRatioTable').innerHTML = country.sexRatio;
    document.getElementById('mobileAccessTable').innerHTML = country.mobileAccess;
    document.getElementById('internetAccessTable').innerHTML = country.internetAccess;

    //----------------- For Government ---------------------------------------
    document.getElementById('chiefTable').innerHTML = country.chiefOfState;
    document.getElementById('govtHeadTable').innerHTML = country.govtHead;
    document.getElementById('govtTypeTable').innerHTML = country.govtType;
    document.getElementById('govtElectTable').innerHTML = country.govtElect;

    //-----------------Social and Health------------------------------------------
    document.getElementById('infantTable').innerHTML = country.infantMortalityRate;
    document.getElementById('lifeExpectancyTable').innerHTML = country.lifeExpectancyAtBirth;
    document.getElementById('healthExpenditureTable').innerHTML = country.heatlthExpenditure;
    document.getElementById('eduExpenditureTable').innerHTML = country.educationExpenditure;
    document.getElementById('literacyTable').innerHTML = country.literacy;
    document.getElementById('adultObesityTable').innerHTML = country.adultObesity;
    //------------------Economic---------------------------------------------------
    document.getElementById('gdpTable').innerHTML = country.gdpPPP;
    document.getElementById('laborForceTable').innerHTML = country.totalLaborForce;
    document.getElementById('publicDebtTable').innerHTML = country.publicDebt;
    document.getElementById('unemploymentTable').innerHTML = country.unemployment;
    document.getElementById('inflationTable').innerHTML = country.inflation;
}


var jsonRequest = function(code3Alpha){
    var httpRequest = new XMLHttpRequest();
    var url = "http://10.11.98.112:9000/displayBasicInfo/"+code3Alpha;
    httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            var country = eval('('+this.responseText+')');
            console.dir(country);
            introTextSetter(country.introText);
            onImgLoad(country.flag);
            setTableElement(country);
            onMapLoad(country);
            plotLanguageProp(country.languagesProps);
            ageStructures(country);
            plotGdpSector(country.gdpBySector);

        }
    };
    httpRequest.open("GET", url , true);
    httpRequest.setRequestHeader('Content-Type','application/json;charset-UTF-8');
    httpRequest.send();
}

var plotLanguageProp = function(langProp){
    if(langProp.length ==  0){
    }
    else{
       // document.getElementById("panneauLng").style.h = "block";
        document.getElementById("panneauLng").hidden = false;
        document.getElementById("btnLang").hidden = false;
        //document.getElementById("btnLang").style.display = "block";
        plotFunction(langProp);
    }
}

var getAlpha3Code = function(){

}

var test = function(lng){
    console.log(lng[0].languageName);
    console.dir(lng);

}
window.onload = function(){
    var code3Alpha = document.getElementById("AlphaCode").innerHTML;
    jsonRequest(code3Alpha);

}

