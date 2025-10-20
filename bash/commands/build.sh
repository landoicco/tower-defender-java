#!/bin/bash

source commands/java.sh


# # Composed commands

demo() {
   create_mods_dir
   build_demo 
}

engine() {
    create_mods_dir
    build_full_engine
}

# Build all in one shot
all() {
    create_mods_dir
    engine
    demo
}

# # Module specific commands

commons() {
    create_mods_dir
    build_engine_commons
}

tools() {
    create_mods_dir
    build_engine_tools
}

awt() {
    create_mods_dir
    build_engine_awt
}
