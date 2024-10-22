package net.helix.core.bukkit.api;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tag {

    public static char[] chars = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static int globalLevels = 0;

    private String nome, tag, color, teamCharacter;
    private int nivel;
    private boolean exclusiva;
    private String[] aliases;

    public Tag(String nome, String tag, String color, boolean exclusiva, String... aliases) {
        this.nome = nome;
        this.tag = tag;
        this.color = color;
        this.exclusiva = exclusiva;

        this.aliases = new String[] {nome};

        setTeam();

        globalLevels++;
        this.nivel = globalLevels;
    }

    public Tag(String nome, String tag, String color, String... aliases) {
        this.nome = nome;
        this.tag = tag;
        this.color = color;
        this.exclusiva = false;

        this.aliases = aliases;

        setTeam();

        globalLevels++;
        this.nivel = globalLevels;
    }

    private void setTeam() {
        try {
            if (globalLevels == 0) {
                this.teamCharacter = "Z";
            } else {
                this.teamCharacter = chars[34 - globalLevels] + "";
            }
        } catch (Exception ex) {
            this.teamCharacter = "Z";
        }
    }
}
