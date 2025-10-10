# Clean prev builds
rm -rf scr/mods

# Create module path directory
mkdir src/mods

# Create commons module
javac \
    --module-path src/mods -d src/commons/target \
    src/commons/licaza/tdefender/commons/objects/**.java \
    src/commons/licaza/tdefender/commons/helpers/**.java \
    src/commons/module-info.java \

jar -cvf src/mods/licaza.tdefender.commons.jar -C src/commons/target .

# Create core module

javac \
    --module-path src/mods -d src/core/target \
    src/core/licaza/tdefender/core/enemies/**.java \
    src/core/licaza/tdefender/core/scenes/**.java \
    src/core/licaza/tdefender/core/ui/**.java \
    src/core/licaza/tdefender/core/managers/**.java \
    src/core/licaza/tdefender/core/inputs/**.java \
    src/core/licaza/tdefender/core/main/**.java \
    src/core/module-info.java \

jar -cvf src/mods/licaza.tdefender.core.jar -C src/core/target .