val fabricLoaderVersion: String by extra
val architecturyVersion: String by extra

dependencies {
    modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    modImplementation("dev.architectury:architectury:$architecturyVersion")
}

architectury {
    common("fabric", "forge", "neoforge")
}

loom {
    accessWidenerPath.set(file("src/main/resources/tbim.accesswidener"))
}
