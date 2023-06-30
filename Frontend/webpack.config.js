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
  module: {
    rules: [
      {
        test: /\.html$/,
        use: 'html-loader',
      },
      {
        test: /\.jsx?$/, // Match both .js and .jsx files
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-react'],
          },
        },
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/main.html',
      filename: 'main.html',
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
