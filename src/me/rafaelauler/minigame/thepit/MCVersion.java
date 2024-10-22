package me.rafaelauler.minigame.thepit;


import org.bukkit.Bukkit;

public enum MCVersion {
  v0("null"),
  v1_7("1.7"),
  v1_8("1.8"),
  v1_9("1.9"),
  v1_10("1.10"),
  v1_11("1.11"),
  v1_12("1.12"),
  v1_13("1.13");
  
  private String version;
  
  MCVersion(String version) {
    this.version = version;
  }
  
  private String getVersion() {
    return this.version;
  }
  
  public static MCVersion get() {
    String v = Bukkit.getVersion().split("MC: ")[1].replaceAll("\\)", "");
    for (MCVersion value : values()) {
      if (v.startsWith(value.getVersion()))
        return value; 
    } 
    return v0;
  }
  
  public boolean isSuperior(MCVersion a) {
    return (ordinal() > a.ordinal());
  }
  
  public boolean isInferior(MCVersion a) {
    return (ordinal() < a.ordinal());
  }
}
