const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true,
  },
  entry: {
    main: './src/pages/main.js',
    usernamecheck: './src/api/UsernameCheck.js',
    tasklist: './src/tasklist.js',
    // Add more entry points for other JavaScript files
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/index.html',
    }),
    new HtmlWebpackPlugin({
      template: './src/tasklist.html',
      filename: 'tasklist.html',
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve(__dirname, 'src/css/style.css'),
          to: 'css',
        },
        {
          from: path.resolve(__dirname, 'src/css/tasklist.css'),
          to: 'css',
        },
        {
          from: path.resolve(__dirname, 'src/sticker.png'),
          to: '',
        },
      ],
    }),
    new CleanWebpackPlugin(),
  ],
};
