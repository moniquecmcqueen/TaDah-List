const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require("copy-webpack-plugin");
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true
  },
  entry: {
    index: path.resolve(__dirname, 'src', 'pages', 'main.js'),
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, 'src', 'index.html'),
      filename: 'index.html',
      inject: 'body',
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve(__dirname, '../Application/src/main/java/com/kenzie/appserver/controller/TaskController.java'),
          to: path.resolve(__dirname, 'dist/java'),
        },
      ],
    }),
    new CleanWebpackPlugin(),
  ],
};
