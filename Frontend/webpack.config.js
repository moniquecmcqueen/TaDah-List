const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
  optimization: {
    usedExports: true,
  },
  entry: {
    main: './src/main.js',
    usernamecheck: './src/UsernameCheck.js',
    tasklist: './src/tasklist.js',
    createaccount: './src/CreateAccount.js',
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

        {
          from: path.resolve(__dirname, 'src/Tada-sound.mp3'),
          to: 'audio',
        },
        {
          from: path.resolve(__dirname, 'src/TaDahLogo.png'),
          to: '',
        },
        {
          from: path.resolve(__dirname, 'src/throwawaytask.mp3'),
          to: 'audio',
        },
        {
          from: path.resolve(__dirname, 'src/taskadded.mp3'),
          to: 'audio',
        },


      ],
    }),
    new CleanWebpackPlugin(),
  ],
};
