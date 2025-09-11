package machines;

public class MachineConfigurationHelper {
    public static String machineConfigurationToString(MachineConfiguration configuration) {
        if(configuration == MachineConfiguration.ProgrammedCircuitOne        ) { return "1";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwo        ) { return "2";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitThree      ) { return "3";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitFour       ) { return "4";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitFive       ) { return "5";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitSix        ) { return "6";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitSeven      ) { return "7";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitEight      ) { return "8";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitNine       ) { return "9";  }
        if(configuration == MachineConfiguration.ProgrammedCircuitTen        ) { return "10"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitEleven     ) { return "11"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwelve     ) { return "12"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitThirteen   ) { return "13"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitFourteen   ) { return "14"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitFifteen    ) { return "15"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitSixteen    ) { return "16"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitSeventeen  ) { return "17"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitEighteen   ) { return "18"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitNineteen   ) { return "19"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwenty     ) { return "20"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwentyOne  ) { return "21"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwentyTwo  ) { return "22"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwentyThree) { return "23"; }
        if(configuration == MachineConfiguration.ProgrammedCircuitTwentyFour ) { return "24"; }

        if(configuration == MachineConfiguration.ProgrammedBioCircuitOne        ) { return "B1";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwo        ) { return "B2";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitThree      ) { return "B3";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitFour       ) { return "B4";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitFive       ) { return "B5";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitSix        ) { return "B6";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitSeven      ) { return "B7";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitEight      ) { return "B8";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitNine       ) { return "B9";  }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTen        ) { return "B10"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitEleven     ) { return "B11"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwelve     ) { return "B12"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitThirteen   ) { return "B13"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitFourteen   ) { return "B14"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitFifteen    ) { return "B15"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitSixteen    ) { return "B16"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitSeventeen  ) { return "B17"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitEighteen   ) { return "B18"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitNineteen   ) { return "B19"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwenty     ) { return "B20"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwentyOne  ) { return "B21"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwentyTwo  ) { return "B22"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwentyThree) { return "B23"; }
        if(configuration == MachineConfiguration.ProgrammedBioCircuitTwentyFour ) { return "B24"; }

        if(configuration == MachineConfiguration.ExtrudeShapeRod ) { return "Extrude"; }

        if(configuration == MachineConfiguration.MoldBall ) { return "Ball"; }

        return "";
    }
}
