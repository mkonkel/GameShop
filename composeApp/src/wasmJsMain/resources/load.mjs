import { instantiate } from './gameshopwasmapp.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });
