package machines;

import register.Namespaces;
import register.Registered;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static register.Namespaces.*;

public class MachineTypes extends Registered<MachineType> {
    private static MachineTypes instance;
    public static final List<MachineType> registry = new ArrayList<>();
    public MachineTypes() {
        super("MachineCategories", registry);
        instance = this;
    }

    public static boolean register(MachineType machine) {
        if(instance == null) {
            instance = new MachineTypes();
        }
        return instance.registerInstance(machine);
    }

    public static boolean isLeafMachine(MachineType machine) {
        return
            machine.equals(PLAYER)
            || machine.equals(CROP_MANAGER)
        ;
    }

    private static final List<Voltage> commonSingleBlockVoltages = new ArrayList<>(); static {
        commonSingleBlockVoltages.add(Voltage.Low);
        commonSingleBlockVoltages.add(Voltage.Medium);
        commonSingleBlockVoltages.add(Voltage.High);
        commonSingleBlockVoltages.add(Voltage.Extreme);
        commonSingleBlockVoltages.add(Voltage.Insane);
        commonSingleBlockVoltages.add(Voltage.Ludicrous);
        commonSingleBlockVoltages.add(Voltage.ZPM);
        commonSingleBlockVoltages.add(Voltage.Ultimate);
        commonSingleBlockVoltages.add(Voltage.HighlyUltimate);
        commonSingleBlockVoltages.add(Voltage.ExtremelyUltimate);
        commonSingleBlockVoltages.add(Voltage.InsanelyUltimate);
        commonSingleBlockVoltages.add(Voltage.MegaUltimate);
    }
    private static HashMap<Voltage, String> getCommonNames(String basicName, String advancedName) {
        HashMap<Voltage, String> commonNames = new HashMap<>();
        commonNames.put(Voltage.Low,                  "Basic "+      basicName            );
        commonNames.put(Voltage.Medium,               "Advanced "+   basicName            );
        commonNames.put(Voltage.High,                 "Advanced "+   basicName    +" II"  );
        commonNames.put(Voltage.Extreme,              "Advanced "+   basicName    +" III" );
        commonNames.put(Voltage.Insane,               "Advanced "+   basicName    +" IV"  );
        commonNames.put(Voltage.Ludicrous,            "Elite "+      basicName            );
        commonNames.put(Voltage.ZPM,                  "Elite"+       basicName    +" II"  );
        commonNames.put(Voltage.Ultimate,             "Ultimate "+   advancedName         );
        commonNames.put(Voltage.HighlyUltimate,       "Epic "+       advancedName         );
        commonNames.put(Voltage.ExtremelyUltimate,    "Epic "+       advancedName  +" II" );
        commonNames.put(Voltage.InsanelyUltimate,     "Epic "+       advancedName  +" III");
        commonNames.put(Voltage.MegaUltimate,         "Epic "+       advancedName  +" IV" );
        return commonNames;
    }

    private static HashMap<Voltage, List<MachineData> > getCommonMachineData(String basicMachineName, String advancedMachineName, MachineData customData) {
        HashMap<Voltage, List<MachineData> > commonMachineData = new HashMap<>();
        HashMap<Voltage, String> names = getCommonNames(basicMachineName, advancedMachineName);
        for(Voltage voltage : names.keySet()) {
            commonMachineData.put(
                voltage,
                List.of(
                    new MachineData(
                        names.get(voltage),
                        customData.getPollutionRate(),
                        customData.getSpeed(),
                        customData.getPowerPercentage()
                    )
                )
            );
        }
        return commonMachineData;
    }
    private static HashMap<Voltage, List<MachineData> > getCommonMachineData(String basicMachineName, String advancedMachineName) {
        return getCommonMachineData(basicMachineName, advancedMachineName, new MachineData(null));
    }
    private static HashMap<Voltage, List<MachineData> > getCommonMachineDataWithDifferentIVName(String basicMachineName, String advancedMachineName, String IVName, MachineData customData) {
        HashMap<Voltage, List<MachineData> > commonMachineData = new HashMap<>();
        HashMap<Voltage, String> names = getCommonNames(basicMachineName, advancedMachineName);
        for(Voltage voltage : names.keySet()) {
            commonMachineData.put(
                voltage,
                List.of(
                    new MachineData(
                        names.get(voltage),
                        customData.getPollutionRate(),
                        customData.getSpeed(),
                        customData.getPowerPercentage()
                    )
                )
            );
        }

        commonMachineData.replace(
            Voltage.Insane,
            List.of(
                new MachineData(
                    IVName,
                    customData.getPollutionRate()-1.0,
                    customData.getSpeed()-1.0,
                    customData.getPowerPercentage()-1.0
                )
            )
        );

        return commonMachineData;
    }
    private static HashMap<Voltage, List<MachineData> > getCommonMachineDataWithDifferentIVName(String basicMachineName, String advancedMachineName, String IVName) {
        return getCommonMachineDataWithDifferentIVName(basicMachineName, advancedMachineName, IVName, new MachineData(null));
    }
    private static HashMap<Voltage, List<MachineData> > getCommonPlusPlusMachineData(String name) {
        HashMap<Voltage, List<MachineData> > commonPlusPlusMachineData = new HashMap<>();
        for( Voltage voltage : Voltage.getVoltagesBetweenInclusive(Voltage.Low, Voltage.Ultimate) ) {
            commonPlusPlusMachineData.put(
                voltage,
                List.of(
                    new MachineData(name+" ("+ voltage.toString() +")")
                )
            );
        }
        return commonPlusPlusMachineData;
    }

    public static final MachineType CELL_CYCLING = new MachineType(WORLD,"Cycling");
    public static final MachineType CONSOLIDATED_BRANCH = new MachineType(Namespaces.PLAYER,"Consolidated Branch");
    public static final MachineType PLAYER = new MachineType(Namespaces.PLAYER,"Manual");

    /*
     * TODO: multi-types
     *  + High-Current Industrial Arc Furnace
     *  + Hot Isostatic Pressuriation Unit
     *  + Pseudostable Black Hole Containment Field
     *  + Industrial Maceration Stack
     *  + Elecrtric Blast Furnace
     */

    //## Multi-Typed Machines
        //TODO: account for unique properties of multi-block
        //TODO: add Blast-Furnace Machine-Type
    private static final MachineData ELECTRIC_BLAST_FURNACE_DATA = new MachineData("Electric Blast Furnace", 400.0);
    private static final MachineData DISTILLATION_TOWER_DATA = new MachineData("Distillation Tower");

    //## Machine Types

    //TODO: Power Sources (Steam & RF); also, Steam-Singleblocks + Steam-Multiblocks
    public static final MachineType ALCHEMICAL_FURNACE; static {
        HashMap<Voltage, List<MachineData> > alchemicalFurnace = new HashMap<>();
        alchemicalFurnace.put(
            Voltage.None,
            List.of( new MachineData("Alchemical Furnace") )
        );

        ALCHEMICAL_FURNACE =  new MachineType(
            THAUMCRAFT,
            "Alchemical Furnace",
            alchemicalFurnace
        );
    }
    public static final MachineType ALLOY_SMELTER; static {
        ALLOY_SMELTER =  new MachineType(
            GREGTECH,
            "Alloy Smelter",
            getCommonMachineData("Alloy Smelter", "Alloy Integrator")
        ); //TODO: Zyngen
    }
    public static final MachineType ARC_FURNACE; static {
        ARC_FURNACE = new MachineType(
            GREGTECH,
            "Arc Furnace",
            getCommonMachineData("Arc Furnace", "Short Circuit Heater")
        );
    }
    public static final MachineType ASSEMBLER; static {
        ASSEMBLER = new MachineType(
            GREGTECH,
            "Assembler",
            getCommonMachineData("Assembling Machine", "Assembly Constructor")
        ); //TODO: Large-Scale Auto-Assembler
    }
    public static final MachineType AUTO_WORKBENCH; static {
        AUTO_WORKBENCH = new MachineType(
            GREGTECH,
            "Auto Workbench",
            getCommonPlusPlusMachineData("Auto Workbench")
        );
    }
    public static final MachineType BENDING_MACHINE; static {
        BENDING_MACHINE = new MachineType(
            GREGTECH,
            "Bending Machine",
            getCommonMachineData("Bending Machine", "Bending Unit")
        ); //TODO: Industrial Material Press
    }
    public static final MachineType CAULDRON; static {
        HashMap<Voltage, List<MachineData> > alchemicalConstruct = new HashMap<>();
        alchemicalConstruct.put(
            Voltage.None,
            List.of(
                new MachineData("Alchemical Construct")
            )
        );

        CAULDRON = new MachineType(
            THAUMCRAFT,
            "Cauldron",
            alchemicalConstruct
        );
    }
    public static final MachineType CENTRIFUGE; static {
        CENTRIFUGE = new MachineType(
            GREGTECH,
            "Centrifuge",
            getCommonMachineData("Centrifuge", "Molecular Tornado")
        ); //TODO: Industrial Centrifuge
    }
    public static final MachineType CHEMICAL_REACTOR; static {
        CHEMICAL_REACTOR = new MachineType(
            GREGTECH,
            "Chemical Reactor",
            getCommonMachineData("Chemical Reactor", "Chemical Performer")
        );
        //TODO: Mega Chemical Reactor
    }
    public static final MachineType CIRCUIT_ASSEMBLER; static {
        HashMap<Voltage, List<MachineData> > circuitAssemblersData = getCommonMachineData("Circuit Assembler", "Circuit Assembling Machine");
        circuitAssemblersData.put(
            Voltage.ExtendedMegaUltimate,
            List.of( new MachineData("Ultimate Circuit Assembling Machine V") )
        );
        circuitAssemblersData.put(
            Voltage.Maximum,
            List.of( new MachineData("Max Circuit Assembling Machine") )
        );
        //TODO: Circuit Assembly Line

        CIRCUIT_ASSEMBLER = new MachineType(GREGTECH, "Circuit Assembler", circuitAssemblersData);
    }
    public static final MachineType COKE_OVEN; static {
        HashMap< Voltage, List<MachineData> > ovens = new HashMap<>();
        ovens.put(
            Voltage.None,
            List.of( new MachineData("Pyrolyze Oven", 300.0) )
        );
        //TODO: Coke Oven, Industrial Coke Oven

        COKE_OVEN = new MachineType(
            GREGTECH,
            "Coke Oven",
            ovens
        );
    }
    public static final MachineType COMPRESSOR; static {
        COMPRESSOR = new MachineType(
            GREGTECH,
            "Compressor",
            getCommonMachineDataWithDifferentIVName("Compressor", "Matter Constrictor", "Singularity Compressor")
        ); //TODO: Large Electric Compressor
    }
    public static final MachineType CROP_MANAGER; static {
        CROP_MANAGER = new MachineType(
                GREGTECH,
                "Crop Manager",
                getCommonPlusPlusMachineData("Crop Manager")
        );
    }
    public static final MachineType DISTILLERY; static {
        DISTILLERY = new MachineType(
            GREGTECH,
            "Distillery",
            getCommonMachineData("Distillery", "Distillation Tower")
        ); //TODO: Distillation Tower, Mega Distillation Tower, Dangote Distillus
    }
    public static final MachineType DISTILLATION_TOWER; static {
        HashMap< Voltage, List<MachineData> > towers = new HashMap<>();
        towers.put(
            Voltage.None,
            List.of(DISTILLATION_TOWER_DATA)
        );
        //TODO: Dangote Distillus, Mega Distillation Tower

        DISTILLATION_TOWER = new MachineType(
            GREGTECH,
            "Distillation Tower",
            towers
        );
    }
    public static final MachineType CRUCIBLE; static {
        HashMap< Voltage, List<MachineData> > crucible = new HashMap<>();
        crucible.put(
            Voltage.None,
            List.of( new MachineData("Crucible") )
        );

        CRUCIBLE = new MachineType(
            THAUMCRAFT,
            "Crucible",
            crucible
        );
    }
    public static final MachineType ELECTRIC_BLAST_FURNACE; static {
        HashMap<Voltage, List<MachineData> > electricBlastFurnaceDatas = new HashMap<>();
        for(Voltage voltage : Voltage.getVoltagesBetweenInclusive(Voltage.Low, Voltage.Maximum) ) {
            electricBlastFurnaceDatas.put(
                voltage,
                List.of(
                    new MachineData(
                        "Electric_Blast_Furance ["+voltage+"]",
                        ELECTRIC_BLAST_FURNACE_DATA.getPollutionRate()
                    )
                )
            );
        }
        ELECTRIC_BLAST_FURNACE = new MachineType(GREGTECH, "Electric Blast Furnace", electricBlastFurnaceDatas);
    }
    public static final MachineType ELECTRIC_FURNACE; static {
        ELECTRIC_FURNACE = new MachineType(
                GREGTECH,
                "Compressor",
                getCommonMachineDataWithDifferentIVName("Electric Furnace", "Atom Stimulator", "Electron Excitement Processor")
            ); //TODO: Microwaves & Ovens; Multi Smelter
        }
    public static final MachineType ELECTROLYZER; static {
        ELECTROLYZER = new MachineType(
            GREGTECH,
            "Electrolyzer",
            getCommonMachineDataWithDifferentIVName("Electrolyzer", "Electrolyzer", "Molecular Disintegrator E-4908")
        ); //TODO: Industrial Electrolyzer
    }
    public static final MachineType EXTRACTOR; static {
        EXTRACTOR = new MachineType(
            GREGTECH,
            "Extractor",
            getCommonMachineDataWithDifferentIVName("Extractor", "Shape Driver", "Vaccum Extractor")
        ); //TODO: Large Processing Factory, Dissection Apparatus, Steam Machines
    }
    public static final MachineType EXTRUDER; static {
        EXTRUDER = new MachineType(
            GREGTECH,
            "Extruder",
            getCommonMachineData("Extruder", "Shape Driver")
        ); //TODO: Industrial Extrusion Machine
    }
    public static final MachineType FLUID_CANNER; static {
        HashMap<Voltage, List<MachineData> > cannersDatas = new HashMap<>(); {
            final String BASIC_NAME  = "Fluid Canner";
            final String ADVANCED_NAME = "Liquid Can Actuator";

            cannersDatas.put(  Voltage.Low,                     List.of(new MachineData("Basic "    +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.Medium,                  List.of(new MachineData("Advanced " +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.High,                    List.of(new MachineData("Quick "    +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.Extreme,                 List.of(new MachineData("Turbo "    +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.Insane,                  List.of(new MachineData("Instant "  +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.Ludicrous,               List.of(new MachineData("Elite "    +BASIC_NAME) )  );
            cannersDatas.put(  Voltage.ZPM,                     List.of(new MachineData("Elite "    +BASIC_NAME+" II") )  );
            cannersDatas.put(  Voltage.Ultimate,                List.of(new MachineData("Ultimate " +ADVANCED_NAME) )  );
            cannersDatas.put(  Voltage.HighlyUltimate,          List.of(new MachineData("Epic "     +ADVANCED_NAME) )  );
            cannersDatas.put(  Voltage.ExtremelyUltimate,       List.of(new MachineData("Epic "     +ADVANCED_NAME+" II") )  );
            cannersDatas.put(  Voltage.InsanelyUltimate,        List.of(new MachineData("Epic "     +ADVANCED_NAME+" III") )  );
            cannersDatas.put(  Voltage.ExtendedMegaUltimate,    List.of(new MachineData("Epic "     +ADVANCED_NAME+" IV") )  );
        }

        FLUID_CANNER = new MachineType(
            GREGTECH,
            "Fluid Canner",
            cannersDatas
        ); //TODO: TurboCan Pro
    }
    public static final MachineType FLUID_EXTRACTOR; static {
        FLUID_EXTRACTOR = new MachineType(
            GREGTECH,
            "Fluid Extractor",
            getCommonMachineData("Fluid Extractor", "Liquefying Sucker")
        ); //TODO: Large Fluid Extractor
    }
    public static final MachineType FLUID_SOLIDIFIER; static {
        FLUID_SOLIDIFIER = new MachineType(
            GREGTECH,
            "Fluid Solidifier",
            getCommonMachineData("Fluid Solidifier", "Fluid Petrificator")
        ); //TODO: Large Processing Factory
    }
    public static final MachineType FLUID_TANK; static {
        HashMap<Voltage, List<MachineData> > fluidTanksData = new HashMap<>();
        for(Voltage voltage : Voltage.getVoltagesBetweenInclusive(Voltage.UltraLow, Voltage.High) ) {
            fluidTanksData.put(
                    voltage,
                    List.of(
                            new MachineData(voltage.toUnabreviatedString()+" Fluid Tank")
                    )
            );
        }

        FLUID_TANK = new MachineType(GREGTECH, "Fluid Tank", fluidTanksData);
    }
    public static final MachineType FORGE_HAMMER; static {
        FORGE_HAMMER = new MachineType(
            GREGTECH,
            "Forge Hammer",
            getCommonMachineData("Forge Hammer", "Impact Modulator")
        ); //TODO: Industrial Sledgehammer
    }
    public static final MachineType LATHE; static {
        LATHE = new MachineType(
            GREGTECH,
            "Lathe",
            getCommonMachineData("Lathe", "Turn-O-Matic")
        ); //TODO: Industrial Precision Lathe
    }
    public static final MachineType LASER_ENGRAVER; static {
        LASER_ENGRAVER = new MachineType(
            GREGTECH,
            "Laser Engraver",
            getCommonMachineData("Laser Engraver", "Exact Photon Cannon")
        ); //TODO: Large Processing Factory, Hyper-Intensity Laser Engraver
    }
    public static final MachineType MACERATOR; static {
        MACERATOR = new MachineType(
            GREGTECH,
            "Macerator",
            getCommonMachineDataWithDifferentIVName("Macerator", "Shape Eliminator", "Blend-O-Matic 9001")
        );
    }
    public static final MachineType MIXER; static {
        MIXER = new MachineType(
            GREGTECH,
            "Mixer",
            getCommonMachineData("Mixer", "Matter Organizer")
        ); //TODO: Industrial Mixing Machine
    }
    public static final MachineType ROCK_BREAKER; static {
        HashMap<Voltage, List<MachineData> > rockBreakersData = getCommonMachineData("Rock Breaker", "Cryogenic Magma Solidifier R-?");
        int big_number = 8200;
        for( Voltage voltage : Voltage.getVoltagesBetweenInclusive(Voltage.Insane, Voltage.MegaUltimate) ) {
            rockBreakersData.replace(
                voltage,
                List.of(
                    new MachineData( "Cryogenic Magma Solidifier R-"+big_number )
                )
            );

            big_number += 1000;
        }
        //TODO: Boldarnator

        ROCK_BREAKER = new MachineType(GREGTECH, "Rock Breaker", rockBreakersData);
    }
    public static final MachineType SIFTER; static {
        SIFTER = new MachineType(
            GREGTECH,
            "Sifter",
            getCommonMachineData("Sifting Machine", "Pulsation Filter")
        ); //TODO: Large Sifter Control Block
    }
    public static final MachineType THIRSTYTANK; static {
        HashMap< Voltage, List<MachineData> > tanks = new HashMap<>(); {
            List<MachineData> guzzlersData = new ArrayList<>();
            String name;
            for(int water_glyphs = 0; water_glyphs < 6; water_glyphs++) {
                name = "Thirsty Tank ("+water_glyphs+" \"Glyph of Guzzler\"s)";
                guzzlersData.add(
                    new MachineData(name, 1.0/Math.pow(0.65,water_glyphs), 0.0)
                );
            }
            tanks.put(Voltage.None, guzzlersData);
        }

        THIRSTYTANK = new MachineType(
            GREGTECH,
            "Thirsty Tank",
            tanks
        );
    }
    public static final MachineType WATERTANK; static {
        HashMap< Voltage, List<MachineData> > tank = new HashMap<>();
        tank.put(
            Voltage.None,
            List.of( new MachineData("Water Tank") )
        );

        WATERTANK = new MachineType(
            RAILCRAFT,
            "Water Tank",
            tank
        );
    }
    public static final MachineType WIREMILL; static {
        WIREMILL = new MachineType(
            GREGTECH,
            "Wiremill",
            getCommonMachineData("Wiremill", "Transfigurator")
        ); //TODO: Industrial Wire Factory
    }

    //TODO: Add missing Machine-Types
    //TODO: Check for new machines in 2.8.x
}