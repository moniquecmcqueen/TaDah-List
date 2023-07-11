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
    '03_signup': './src/signup.js'
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
  module: {
    rules: [
      {
        test: /\.(gif|mp3)$/i,
        use: [
          {
            loader: 'file-loader',
            options: {
              name: '[name].[ext]',
              outputPath: 'assets'
            }
          }
        ]
      }
    ]
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
    new CleanWebpackPlugin()
  ]
};
