

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
        // method: "GET"
      },
      '/auth/github/callback': {
        target: 'http://localhost:8080/auth/github/callback',
        changeOrigin: true,
      },
      // '/post': {
      //   target: 'http://localhost:8080/post',
      //   changeOrigin: true

      // },
      // '/post': {
      //   target: 'http://localhost:8080/post',
      //   changeOrigin: true 
      // }
    }
  }
};