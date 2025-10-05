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
    --module-path mods -d src/models/target \
    src/models/dev/lando/tdefender/models/objects/**.java \
    src/models/module-info.java \

jar -cvf src/mods/models.jar -C src/models/target .