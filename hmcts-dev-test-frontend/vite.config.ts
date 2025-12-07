import { defineConfig } from 'vitest/config';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './src/test/setup.ts', // optional
    include: ['src/test/**/*.test.{ts,tsx}'], // ensure Vitest sees your test files
  },
  server: {
    proxy: {
      '/tasks': 'http://localhost:4000',
    },
  },
});
