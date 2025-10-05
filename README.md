# Production Map

---

### Example Map
![Example Map](./readme_images/ElectronicCircuit_LV_1.0.0.png "Electronic Circuit, EBF-limited, via Wrought-Iron and Redstone-Alloy")

This program is used to make readable Production-lines as inverted Trees, interpreted as inputs/outputs between machines. This tree can be used to \[manually\] produce images of Production-Lines.

---

### Example Output

```java
//"Input"
public class Main {
    public static void main(String[] args) {
        System.out.println( Maps.ELECTRONIC_CIRCUIT );
    }
}
```

NodeGraph(electric_circuit) \{
<details>
  <summary>    Products:</summary>
    <pre style="font-family: monospace; line-height: 0.5rem; margin: 0;">
    0 -> Electric_circuit @ 0.0167/second<br>
    1 -> Circuit_board @ 0.0167/second<br>
    2 -> Resistor @ 0.0333/second<br>
    3 -> 1x_red_alloy_wire @ 0.0333/second<br>
    4 -> Vacuum_tube @ 0.0333/second<br>
    5 -> Molten_lead @ 0.0048/second<br>
    6 -> Wood_plank @ 0.0167/second<br>
    7 -> Copper_foil @ 0.0667/second<br>
    8 -> Refined_glue @ 6.7503/second<br>
    9 -> Coal_dust @ 0.009/second (unautomated)<br>
    10 -> Fine_copper_wire @ 0.0333/second<br>
    11 -> 1x_copper_wire @ 0.05/second<br>
    12 -> Red_alloy_ingot @ 0.0167/second<br>
    13 -> Glass_tube @ 0.0167/second<br>
    14 -> Steel_rod @ 0.0167/second<br>
    15 -> Molten_redstone_alloy @ 0.0021/second<br>
    16 -> Lead_ingot @ 0.0333/second (unautomated)<br>
    17 -> Wood_pulp @ 0.0167/second<br>
    18 -> Copper_ingot @ 0.0667/second<br>
    19 -> Sticky_resin @ 9.7204/second (unautomated)<br>
    20 -> Raw_rubber_dust @ 29.1613/second<br>
    21 -> Plant_ball @ 0.972/second<br>
    22 -> Redstone_dust @ 0.0984/second<br>
    23 -> Glass_dust @ 0.0167/second<br>
    24 -> Steel_ingot @ 0.0083/second<br>
    25 -> Redstone_alloy_ingot @ 0.0021/second<br>
    26 -> Any_log @ 0.0028/second (unautomated)<br>
    27 -> Copper_dust @ 0.0667/second (unautomated)<br>
    28 -> Glowstone_dust @ 0.1333/second<br>
    29 -> Gold_dust @ 0.0984/second<br>
    30 -> Flint_dust @ 0.001/second<br>
    31 -> Quartz_sand @ 0.0167/second<br>
    32 -> Wrought_iron_dust @ 0.0083/second<br>
    33 -> Oxygen @ 9.0371/second<br>
    34 -> Ashes @ 9.0E-4/second<br>
    35 -> Redstone_alloy_dust @ 0.0021/second<br>
    36 -> Glow_flower @ 0.2667/second (unautomated)<br>
    37 -> Flint @ 0.0021/second<br>
    38 -> Sand @ 0.0167/second<br>
    39 -> Wrought_iron_ingot @ 0.0083/second<br>
    40 -> Oxygen_cell @ 8.9704/second<br>
    41 -> Empty_cell @ 53.8225/second<br>
    42 -> Raw_silicon_dust @ 0.0167/second<br>
    43 -> Gravel @ 0.0172/second<br>
    44 -> Iron_dust @ 0.0083/second<br>
    45 -> Air @ 89.7042/second<br>
    46 -> Nitrogen @ 34.9846/second<br>
    47 -> Obsidian_dust @ 0.1/second<br>
    48 -> Magnesium_dust @ 0.0083/second<br>
    49 -> Cobblestone @ 0.0172/second<br>
    50 -> Compressed_air @ 44.8521/second<br>
    51 -> Obsidian @ 0.0083/second<br>
    </pre>
</details>
<details>
  <summary>    Transformers:</summary>
    <pre style="font-family: monospace; line-height: 0.5rem; margin: 0;">
    0 -> Assembler(Circuit_board + 2×Resistor + 2×1x_red_alloy_wire + 2×Vacuum_tube + 0.288×Molten_lead) = Electric_circuit @ 0.83%<br>
    1 -> Assembler(8×Wood_plank + 32×Copper_foil + 4×Refined_glue) = 8×Circuit_board @ 0.83%<br>
    2 -> Circuit-Assembler(Coal_dust + 4×Fine_copper_wire + 4×1x_copper_wire + 0.288×Refined_glue) = 4×Resistor @ 0.67%<br>
    3 -> Wiremill(Red_alloy_ingot) = 2×1x_red_alloy_wire @ 0.42%<br>
    4 -> Assembler(4×Glass_tube + 4×1x_copper_wire + 4×Steel_rod + 0.5×Molten_redstone_alloy) = 8×Vacuum_tube @ 0.17%<br>
    5 -> Fluid-Extractor(Lead_ingot) = 0.144×Molten_lead @ 0.2%<br>
    6 -> Assembler(64×Wood_pulp + Refined_glue) = 64×Wood_plank @ 0.16%<br>
    7 -> Bending-Machine(Copper_ingot) = 4×Copper_foil @ 0.53%<br>
    8 -> Centrifuge(Sticky_resin) = 3×Raw_rubber_dust + 0.1×Plant_ball + 0.6944×Refined_glue @ 2.43%<br>
    9 -> Manual() = Coal_dust (manual)<br>
    10 -> Wiremill(Copper_ingot) = 4×Fine_copper_wire @ 0.21%<br>
    11 -> Wiremill(Copper_ingot) = 2×1x_copper_wire @ 0.63%<br>
    12 -> Alloy-Smelter(4×Redstone_dust + Copper_ingot) = Red_alloy_ingot @ 0.21%<br>
    13 -> Alloy-Smelter(Glass_dust) = Glass_tube @ 0.5%<br>
    14 -> Extruder(Steel_ingot) = 2×Steel_rod @ 0.23%<br>
    15 -> Fluid-Extractor(Redstone_alloy_ingot) = Molten_redstone_alloy @ 0.01%<br>
    16 -> Manual() = Lead_ingot (manual)<br>
    17 -> Macerator(Any_log) = 6×Wood_pulp @ 0.28%<br>
    18 -> Compressor(Copper_dust) = Copper_ingot @ 2.13%<br>
    19 -> Crop-Manager() = Sticky_resin @ 48.6%<br>
    20 -> Centrifuge(2×Glowstone_dust) = Redstone_dust + Gold_dust @ 24.02%<br>
    21 -> Mixer(Flint_dust + 16×Quartz_sand) = 16×Glass_dust @ 0.21%<br>
    22 -> Electric-Blast-Furnace(Wrought_iron_dust + Oxygen) = Steel_ingot + 0.1111×Ashes @ 0.21%<br>
    23 -> Electric-Blast-Furnace(Redstone_alloy_dust + Oxygen) = Redstone_alloy_ingot @ 0.42%<br>
    24 -> Crop-Manager() = Any_log @ 0.01%<br>
    25 -> Manual() = Copper_dust (manual)<br>
    26 -> Fluid-Extractor(2×Glow_flower) = Glowstone_dust @ 10.0%<br>
    27 -> Macerator(2×Flint) = Flint_dust @ 0.05%<br>
    28 -> Macerator(Sand) = Quartz_sand @ 0.83%<br>
    29 -> Macerator(Wrought_iron_ingot) = Wrought_iron_dust @ 0.12%<br>
    30 -> Fluid-Tank(Oxygen_cell) = Empty_cell + Oxygen @ 4.49%<br>
    31 -> Mixer(Redstone_dust + Raw_silicon_dust + Coal_dust) = 3×Redstone_alloy_dust @ 0.02%<br>
    32 -> Crop-Manager() = Glow_flower @ 1.33%<br>
    33 -> Sifter(Gravel) = 3.88×Flint @ 0.08%<br>
    34 -> Forge-Hammer(Gravel) = Sand @ 0.04%<br>
    35 -> Arc-Furnace(Iron_dust + 0.056×Oxygen) = Wrought_iron_ingot @ 0.12%<br>
    36 -> Centrifuge(10×Air + Empty_cell) = Oxygen_cell + 3.9×Nitrogen @ 3588.17%<br>
    37 -> Electrolyzer(12×Obsidian_dust) = Magnesium_dust + Iron_dust + 2×Raw_silicon_dust + 8×Oxygen @ 0.5%<br>
    38 -> Forge-Hammer(Cobblestone) = Gravel @ 0.04%<br>
    39 -> Fluid-Tank(Compressed_air) = Empty_cell + 2×Air @ 22.43%<br>
    40 -> Macerator(Obsidian) = 12×Obsidian_dust @ 0.41%<br>
    41 -> Rock-Breaker() = Cobblestone @ 0.07%<br>
    42 -> Compressor(Empty_cell) = Compressed_air @ 3363.91%<br>
    43 -> Rock-Breaker(Redstone_dust) = Obsidian @ 0.27%<br>
    </pre>
</details>
<details>
  <summary>    Machine Counts:</summary>
    <pre style="font-family: monospace; line-height: 0.5rem; margin: 0;">
    2×Ultra Low Voltage Fluid Tank (ULV Fluid-Tank) @ ~13.4556%<br>
    4×Basic Assembling Machine (LV Assembler) @ ~0.4974%<br>
    1×Basic Fluid Extractor (LV Fluid-Extractor) @ ~10.0%<br>
    2×Advanced Fluid Extractor (MV Fluid-Extractor) @ ~0.1063%<br>
    2×Electric_Blast_Furance [MV] (MV Electric-Blast-Furnace) @ ~0.3125%<br>
    3×Crop Manager (LV) (LV Crop-Manager) @ ~16.6498%<br>
    2×Basic Mixer (LV Mixer) @ ~0.1128%<br>
    2×Basic Forge Hammer (LV Forge-Hammer) @ ~0.0423%<br>
    1×Advanced Arc Furnace (MV Arc-Furnace) @ ~0.1167%<br>
    1×Advanced Electrolyzer (MV Electrolyzer) @ ~0.5%<br>
    2×Basic Rock Breaker (LV Rock-Breaker) @ ~0.1677%<br>
    1×Basic Circuit Assembler (LV Circuit-Assembler) @ ~0.6667%<br>
    1×Basic Electric Furnace (LV Compressor) @ ~2.1333%<br>
    38×Basic Centrifuge (LV Centrifuge) @ ~95.1214%<br>
    1×Advanced Extruder (MV Extruder) @ ~0.2333%<br>
    2×Basic Alloy Smelter (LV Alloy-Smelter) @ ~0.3542%<br>
    1×Basic Bending Machine (LV Bending-Machine) @ ~0.525%<br>
    5×Basic Macerator (LV Macerator) @ ~0.3385%<br>
    34×Basic Compressor (LV Compressor) @ ~98.9384%<br>
    3×Basic Wiremill (LV Wiremill) @ ~0.4167%<br>
    1×Basic Sifting Machine (LV Sifter) @ ~0.0805%<br>
    </pre>
</details>
<details>
  <summary>    Other</summary>
    <pre style="font-family: monospace; line-height: 0.5rem; margin: 0;">
    Average Power Usage:<br>
        LV: ~364.1063 EU/t<br>
        MV: ~1.5875 EU/t<br>
    Maximum Power Usage:<br>
        LV: 707 EU/t<br>
        MV: 580 EU/t<br>
<br>
    Average Pollution Output:<br>
        ~2.5 pollution/second<br>
    Maximum Pollution Output:<br>
        800 pollution/second<br>
    </pre>
</details>
}


---

### Features:
- Automatically generates Manufacturing-Process for a Product.
- Discovers \[and separates\] common sub-processes into separate lines (minimum-depth is configurable).
- Calculates uptimes of entire Manufacturing-Process.

### Planned Features:
- Product-input Information (manual inputs for producing one (1) product).
- Reformatting Machines such that there can exist multiple implementations for different systems of Machines.
- Multi-location inputs (by priority); maybe allowing for useful byproducts to be utilized.

### Known Bug(s):
- Multi-product sub-processes may overwrite an existing one.
