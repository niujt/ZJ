

module.exports = {
    apps: [{
      name: 'zj-server',
      script: 'java',
      args: ['-jar', '-Xms128m', '-Xmx2g', 'ZjAction.jar'],
      env: {
          PORT: '8088',
          MYSQL_HOST: '192.168.3.94',
          USER_NAME: 'root',
          PASSWORD: '123456'
      }
    }
]
};

