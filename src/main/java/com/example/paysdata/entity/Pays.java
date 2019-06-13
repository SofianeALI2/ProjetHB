package com.example.paysdata.entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private int numeroCode;
    private long population;
    private String name,alpha2Code, alpha3Code,capital,altspelling,region,
            subregion, demonym,currencies,langage,flag;
    @ElementCollection
    @CollectionTable(
            name="borders",
            joinColumns=@JoinColumn(name="OWNER_ID")
    )

    private List<String> borders;
    private String ciaDataName;
    @Column(columnDefinition="LONGTEXT")
    private String introText ;
    private String ciaCode;
    private double zero14;
    private double fifteen24;
    private double twentyFive54;
    private double fiftyFive64;
    private double over65;
    private double medianAge;
    private double birthRate;
    private double deathRate;
    private double urbanPop;
    private double sexRatio;
    private double mobileAccess;
    private double internetAccess;
    @OneToMany(cascade = CascadeType.ALL)
    private List<LanguagesProp> languagesProps;
    @Column(columnDefinition="LONGTEXT")
    private String govtType;
    @Column(columnDefinition="LONGTEXT")
    private String chiefOfState;
    @Column(columnDefinition="LONGTEXT")
    private String govtHead;
    @Column(columnDefinition="LONGTEXT")
    private String govtElect;
    private double infantMortalityRate;
    private double lifeExpectancyAtBirth;
    private double heatlthExpenditure;
    private double adultObesity;
    private double educationExpenditure;
    private double literacy;
    private double gdpPPP;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "agriculture",
                    column = @Column(name = "gdpAgriculture")
            ),@AttributeOverride(
            name = "industry",
            column = @Column(name = "gdpIndustry")
            ),@AttributeOverride(
            name = "services",
            column = @Column(name = "gdpServices"))
    }
    )
    private Sectorial gdpBySector;
    private double totalLaborForce;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "agriculture",
                    column = @Column(name = "laborAgriculture")
            ),@AttributeOverride(
            name = "industry",
            column = @Column(name = "laborIndustry")
    ),@AttributeOverride(
            name = "services",
            column = @Column(name = "laborServices"))
    }
    )
    private Sectorial laborForceBySector;
    private double publicDebt;
    private double unemployment;
    private double inflation;




    public Pays() {

    }

   /* public Pays(int numeroCode, String name, String alpha2Code, String alpha3Code, String capital, String altspelling, String region, String subregion, long population, String demonym,
                String currencies, String langage, String flag, List<String> borders) {
        this.numeroCode = numeroCode;
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.altspelling = altspelling;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.demonym = demonym;
        this.currencies = currencies;
        this.langage = langage;
        this.flag = flag;
        this.borders = borders;
        this.ciaDataName = null;
        this.introText = null;
        this.ciaCode = null;
        this.zero14= 0.0;
        this.fifteen24= 0.0;
        this.twentyFive54= 0.0;
        this.fiftyFive64= 0.0;
        this.over65= 0.0;
        this.medianAge=0;
        this.birthRate=0;
        this.deathRate=0;
        this.urbanPop=0;
        this.sexRatio=0;
        this.mobileAccess=0;
        this.internetAccess=0;
        this.languagesProps = null;
        this.govtType = "";
        this.chiefOfState = "";
        this.govtHead = "";
        this.govtElect = "";
        this.infantMortalityRate=0;
        this.lifeExpectancyAtBirth = 0;
        this.heatlthExpenditure=0;
        this.adultObesity=0;
        this.educationExpenditure=0;
        this.literacy=0;

    }
*/
    public String getCiaDataName() {
        return ciaDataName;
    }

    public void setCiaDataName(String ciaDataName) {
        this.ciaDataName = ciaDataName;
    }

    public double getZero14() {
        return zero14;
    }

    public List<LanguagesProp> getLanguagesProps() {
        return languagesProps;
    }

    public void setLanguagesProps(List<LanguagesProp> languagesProps) {
        this.languagesProps = languagesProps;
    }

    public void setZero14(double zero14) {
        this.zero14 = zero14;
    }



    public double getFifteen24() {
        return fifteen24;
    }

    public void setFifteen24(double fifteen24) {
        this.fifteen24 = fifteen24;
    }

    public double getTwentyFive54() {
        return twentyFive54;
    }

    public void setTwentyFive54(double twentyFive54) {
        this.twentyFive54 = twentyFive54;
    }

    public double getFiftyFive64() {
        return fiftyFive64;
    }

    public void setFiftyFive64(double fiftyFive64) {
        this.fiftyFive64 = fiftyFive64;
    }

    public double getOver65() {
        return over65;
    }

    public void setOver65(double over65) {
        this.over65 = over65;
    }

    public double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(double medianAge) {
        this.medianAge = medianAge;
    }

    public double getBirthRate() {
        return birthRate;
    }

    public void setBirthRate(double birthRate) {
        this.birthRate = birthRate;
    }

    public double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(double deathRate) {
        this.deathRate = deathRate;
    }

    public double getUrbanPop() {
        return urbanPop;
    }

    public void setUrbanPop(double urbanPop) {
        this.urbanPop = urbanPop;
    }

    public double getSexRatio() {
        return sexRatio;
    }

    public void setSexRatio(double sexRatio) {
        this.sexRatio = sexRatio;
    }

    public double getMobileAccess() {
        return mobileAccess;
    }

    public void setMobileAccess(double mobileAccess) {
        this.mobileAccess = mobileAccess;
    }

    public double getInternetAccess() {
        return internetAccess;
    }

    public void setInternetAccess(double internetAccess) {
        this.internetAccess = internetAccess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCiaCode() {
        return ciaCode;
    }

    public void setCiaCode(String ciaCode) {
        this.ciaCode = ciaCode;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public int getNumeroCode() {
        return numeroCode;
    }

    public void setNumeroCode(int numeroCode) {
        this.numeroCode = numeroCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getAltspelling() {
        return altspelling;
    }

    public void setAltspelling(String altspelling) {
        this.altspelling = altspelling;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", numeroCode=" + numeroCode +
                ", name='" + name + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", capital='" + capital + '\'' +
                ", altspelling='" + altspelling + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", demonym='" + demonym + '\'' +
                ", currencies='" + currencies + '\'' +
                ", langage='" + langage + '\'' +
                ", flag='" + flag + '\'' +
                ", borders=" + borders +
                '}';
    }

    public String getGovtType() {
        return govtType;
    }

    public void setGovtType(String govtType) {
        this.govtType = govtType;
    }

    public String getChiefOfState() {
        return chiefOfState;
    }

    public void setChiefOfState(String chiefOfState) {
        this.chiefOfState = chiefOfState;
    }

    public String getGovtHead() {
        return govtHead;
    }

    public void setGovtHead(String govtHead) {
        this.govtHead = govtHead;
    }

    public String getGovtElect() {
        return govtElect;
    }

    public void setGovtElect(String govtElect) {
        this.govtElect = govtElect;
    }

    public String toJson(){
        Gson gson = new Gson();
        return(gson.toJson(this));
    }

    public double getInfantMortalityRate() {
        return infantMortalityRate;
    }

    public void setInfantMortalityRate(double infantMortalityRate) {
        this.infantMortalityRate = infantMortalityRate;
    }

    public double getLifeExpectancyAtBirth() {
        return lifeExpectancyAtBirth;
    }

    public void setLifeExpectancyAtBirth(double lifeExpectancyAtBirth) {
        this.lifeExpectancyAtBirth = lifeExpectancyAtBirth;
    }

    public double getHeatlthExpenditure() {
        return heatlthExpenditure;
    }

    public void setHeatlthExpenditure(double heatlthExpenditure) {
        this.heatlthExpenditure = heatlthExpenditure;
    }

    public double getAdultObesity() {
        return adultObesity;
    }

    public void setAdultObesity(double adultObesity) {
        this.adultObesity = adultObesity;
    }

    public double getEducationExpenditure() {
        return educationExpenditure;
    }

    public void setEducationExpenditure(double educationExpenditure) {
        this.educationExpenditure = educationExpenditure;
    }

    public double getLiteracy() {
        return literacy;
    }

    public void setLiteracy(double literacy) {
        this.literacy = literacy;
    }

    public double getGdpPPP() {
        return gdpPPP;
    }

    public void setGdpPPP(double gdpPPP) {
        this.gdpPPP = gdpPPP;
    }

    public Sectorial getGdpBySector() {
        return gdpBySector;
    }

    public void setGdpBySector(Sectorial gdpBySector) {
        this.gdpBySector = gdpBySector;
    }

    public double getTotalLaborForce() {
        return totalLaborForce;
    }

    public void setTotalLaborForce(double totalLaborForce) {
        this.totalLaborForce = totalLaborForce;
    }

    public Sectorial getLaborForceBySector() {
        return laborForceBySector;
    }

    public void setLaborForceBySector(Sectorial laborForceBySector) {
        this.laborForceBySector = laborForceBySector;
    }

    public double getPublicDebt() {
        return publicDebt;
    }

    public void setPublicDebt(double publicDebt) {
        this.publicDebt = publicDebt;
    }

    public double getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(double unemployment) {
        this.unemployment = unemployment;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }
}
