// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = {
  devServer: {
    proxy: {
      
      // '/': {
      //   target: 'https://api.large-devlog.com/',
      //   changeOrigin: true,
      //   // method: "GET"
      // },

    
    }
  }
};