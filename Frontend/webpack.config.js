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
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].js',
  },
  devServer: {
    https: false,
    port: 8080,
    open: true,
  },
/*  //proxy is what tells your frontend where to find the backend and what requests to send there
  //if you  notice in the example we are sending all requests that start with /example to
  //http://localhost:5001 which is where the backend is, when sent to the backend it will become
  //http://localhost:5001/exemple/...
  //for example if you sent the request /example/bob to the backend, it will be converted into
  //http://localhost:5001/example/bob and sent to the backend that way.
  //uncomment the following proxy section to make the example work
//    proxy: [
//          {
//            context: [
//              '/example',
//            ],
//            target: 'http://localhost:5001'
//          }
//        ]*/
  module: {
    rules: [
      {
        test: /\.html$/,
        use: 'html-loader',
      },
      // Exclude Java files from being processed by loaders
      {
        test: /\.java$/,
        exclude: /node_modules/,
        use: 'ignore-loader',
      },
      // Other rules for handling JavaScript, CSS, etc.
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './src/main.html',
      filename: 'main.html',
      inject: false,
    }),
    new CopyPlugin({
      patterns: [
        {
          from: path.resolve('src/css'),
          to: path.resolve("dist/css"),
        },
        // Include your compiled Java classes here
        {
          from: path.resolve('path/to/compiled/java/classes'),
          to: path.resolve("dist/java"),
        },
      ],
    }),
    new CleanWebpackPlugin(),
  ],
};
