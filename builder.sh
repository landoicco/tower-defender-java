# Clean prev builds
rm -rf scr/mods

# Create module path directory
mkdir src/mods

# Create utils module
# javac \
#     --module-path mods -d src/utils/target \
#     src/util/helpers/**.java \

# Create models module
javac \
    --module-path mods -d src/commons/target \
    src/commons/licaza/tdefender/commons/objects/**.java \
    src/commons/licaza/tdefender/commons/helpers/**.java \
    src/commons/module-info.java \

jar -cvf src/mods/licaza.tdefender.commons.jar -C src/commons/target .