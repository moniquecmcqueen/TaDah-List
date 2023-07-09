const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    '01_checkusername': './src/checkusername.js',
    '02_tasklist': './src/tasklist.js',
    '03_signup': './src/signup.js',
    '04_childtasklist': './src/childtasklist.js'
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
    // Proxy configuration (uncomment if necessary)
    // proxy: {
    //   '/example': 'http://localhost:5001'
    // }
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
      filename: 'index.html',
      inject: true // Set inject to true for script injection

    }),
    new CopyPlugin({
      patterns: [
        {
          from: 'src/css',
          to: 'css'
        }
      ]
    }),
    new HtmlWebpackPlugin({
      template: './src/tasklist.html',
      filename: 'tasklist.html',
      inject: true // Set inject to true for script injection
    }),

    new HtmlWebpackPlugin({
      template: './src/signup.html',
      filename: 'signup.html',
      inject: true // Set inject to true for script injection
    }),
    new HtmlWebpackPlugin({
      template: './src/childTaskList.html',
      filename: 'childTaskList.html',
      inject: true // Set inject to true for script injection
    }),


    //       // from: path.resolve('src/css'),
    //       // to: path.resolve("dist/css")
    //     }
    //   ]
    //
    // }),

    new CleanWebpackPlugin()
  ]
};
