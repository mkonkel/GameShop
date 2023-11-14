class WasmPlatform : Platform {
    override val name: String = "WASM"
}

actual fun getPlatform(): Platform = WasmPlatform()