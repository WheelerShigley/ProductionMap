package machines;

public enum MachineConfiguration {
    None,

    ProgrammedCircuitOne,
    ProgrammedCircuitTwo,
    ProgrammedCircuitThree,
    ProgrammedCircuitFour,
    ProgrammedCircuitFive,
    ProgrammedCircuitSix,
    ProgrammedCircuitSeven,
    ProgrammedCircuitEight,
    ProgrammedCircuitNine,
    ProgrammedCircuitTen,
    ProgrammedCircuitEleven,
    ProgrammedCircuitTwelve,
    ProgrammedCircuitThirteen,
    ProgrammedCircuitFourteen,
    ProgrammedCircuitFifteen,
    ProgrammedCircuitSixteen,
    ProgrammedCircuitSeventeen,
    ProgrammedCircuitEighteen,
    ProgrammedCircuitNineteen,
    ProgrammedCircuitTwenty,
    ProgrammedCircuitTwentyOne,
    ProgrammedCircuitTwentyTwo,
    ProgrammedCircuitTwentyThree,
    ProgrammedCircuitTwentyFour,

    ProgrammedBioCircuitOne,
    ProgrammedBioCircuitTwo,
    ProgrammedBioCircuitThree,
    ProgrammedBioCircuitFour,
    ProgrammedBioCircuitFive,
    ProgrammedBioCircuitSix,
    ProgrammedBioCircuitSeven,
    ProgrammedBioCircuitEight,
    ProgrammedBioCircuitNine,
    ProgrammedBioCircuitTen,
    ProgrammedBioCircuitEleven,
    ProgrammedBioCircuitTwelve,
    ProgrammedBioCircuitThirteen,
    ProgrammedBioCircuitFourteen,
    ProgrammedBioCircuitFifteen,
    ProgrammedBioCircuitSixteen,
    ProgrammedBioCircuitSeventeen,
    ProgrammedBioCircuitEighteen,
    ProgrammedBioCircuitNineteen,
    ProgrammedBioCircuitTwenty,
    ProgrammedBioCircuitTwentyOne,
    ProgrammedBioCircuitTwentyTwo,
    ProgrammedBioCircuitTwentyThree,
    ProgrammedBioCircuitTwentyFour,

    MoldBall,
    MoldBlock,

    ExtrudeShapeRod
    ;

    @Override
    public String toString() {
        return switch(this) {
            case ProgrammedCircuitOne -> "1"; case ProgrammedCircuitTwo -> "2"; case ProgrammedCircuitThree -> "3";
            case ProgrammedCircuitFour -> "4"; case ProgrammedCircuitFive -> "5"; case ProgrammedCircuitSix -> "6";
            case ProgrammedCircuitSeven -> "7"; case ProgrammedCircuitEight -> "8"; case ProgrammedCircuitNine -> "9";
            case ProgrammedCircuitTen -> "10"; case ProgrammedCircuitEleven -> "11"; case ProgrammedCircuitTwelve -> "12";
            case ProgrammedCircuitThirteen -> "13"; case ProgrammedCircuitFourteen -> "14"; case ProgrammedCircuitFifteen -> "15";
            case ProgrammedCircuitSixteen -> "16"; case ProgrammedCircuitSeventeen -> "17"; case ProgrammedCircuitEighteen -> "18";
            case ProgrammedCircuitNineteen -> "19"; case ProgrammedCircuitTwenty -> "20"; case ProgrammedCircuitTwentyOne -> "21";
            case ProgrammedCircuitTwentyTwo -> "22"; case ProgrammedCircuitTwentyThree -> "23"; case ProgrammedCircuitTwentyFour -> "24";

            case ProgrammedBioCircuitOne -> "B1"; case ProgrammedBioCircuitTwo -> "B2"; case ProgrammedBioCircuitThree -> "B3";
            case ProgrammedBioCircuitFour -> "B4"; case ProgrammedBioCircuitFive -> "B5"; case ProgrammedBioCircuitSix -> "B6";
            case ProgrammedBioCircuitSeven -> "B7"; case ProgrammedBioCircuitEight -> "B8"; case ProgrammedBioCircuitNine -> "B9";
            case ProgrammedBioCircuitTen -> "B10"; case ProgrammedBioCircuitEleven -> "B11"; case ProgrammedBioCircuitTwelve -> "B12";
            case ProgrammedBioCircuitThirteen -> "B13"; case ProgrammedBioCircuitFourteen -> "B14"; case ProgrammedBioCircuitFifteen -> "B15";
            case ProgrammedBioCircuitSixteen -> "B16"; case ProgrammedBioCircuitSeventeen -> "B17"; case ProgrammedBioCircuitEighteen -> "B18";
            case ProgrammedBioCircuitNineteen -> "B19"; case ProgrammedBioCircuitTwenty -> "B20"; case ProgrammedBioCircuitTwentyOne -> "B21";
            case ProgrammedBioCircuitTwentyTwo -> "B22"; case ProgrammedBioCircuitTwentyThree -> "B23"; case ProgrammedBioCircuitTwentyFour -> "B24";

            //TODO: add missing molds
            case MoldBall -> "Ball";

            //TODO: add missing extrude shapes
            case ExtrudeShapeRod -> "Rod";

            default -> "";
        };
    }
}
