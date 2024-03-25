const { defineConfig } = require('@vue/cli-service')

// vue.config.js
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')

module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "../src/main/resources/static",  // 빌드 타겟 디렉토리
  devServer: { 
    proxy: { 
      '/api': { 
        target: 'http://localhost:8081/api',
        changeOrigin: true, // cross origin 허용
        pathRewrite: { 
          '^/api': ''
        } 
      } 
    } 
  },
  configureWebpack: {
    plugins: [
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
    ],
  },
  css: {
    loaderOptions: {
      // 사용하는 전처리기에 따라 설정
      sass: {
        additionalData: `@import "@/styles/variables.scss";`
      },
      less: {
        additionalData: `@import "@/styles/variables.less";`
      }
    }
  }
})
