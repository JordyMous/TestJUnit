<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns="http://www.w3.org/1999/xhtml">

<xsl:template match="/">

<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
  <meta name="description" content="Report template that is used to show test result from the Switch4 Test Automation Framework "/>
  <title>BrightReport</title>
  
  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
      <a class="navbar-brand" href="#">BrightReport</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">
   <div class="row">
      <div class="col-lg-12 text-center">
        <div class="container">
        <h2 class="mt-5">Overview</h2>
          <div class="row">
            <div class="col">
              <canvas id="myChart"></canvas>
            </div>
            <div class="col">
              <canvas id="myChart2"></canvas>
            </div>
          </div>
        <div class="col-md-auto">
           <div class="card-body">
            <table class="table table-hover">
                <thead>
                  <tr>
                    <th class="table-active" scope="col">Passed</th>
                    <th class="table-active" scope="col">Failed</th>
                    <th class="table-active" scope="col">Aborted</th>
                    <th class="table-active" scope="col">Total</th>
                    <th class="table-active" scope="col">Disabled</th>
                  </tr>
                </thead>
                <tbody>
                <td><xsl:value-of select="count(/testsuite/testcase[status='passed'])"/></td>
                <td><xsl:value-of select="count(/testsuite/testcase[status='failed'])"/></td>
                <td><xsl:value-of select="count(/testsuite/testcase[status='aborted'])"/></td>
                <td><xsl:value-of select="count(/testsuite/testcase) - count(/testsuite/testcase[status='disabled'])"/></td>
                <td><xsl:value-of select="count(/testsuite/testcase[status='disabled'])"/></td>
                </tbody>
            </table>
          </div>
            </div>
            <div class="col-md-auto">
            <h2 class="mt-5">All Test Cases</h2>
              <div class="card-body">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th class="table-active" scope="col">#</th>
                    <th class="table-active" scope="col">Name</th>
                    <th class="table-active" scope="col">Status</th>
                    <th class="table-active" scope="col">Execution Time</th>
                    <th class="table-active" scope="col">Screenshot</th>
                  </tr>
                </thead>
                <tbody>
                  <xsl:for-each select="testsuite/testcase">
                  <tr>
                    <th scope="row"><xsl:value-of select = "position()" /></th>
                    <td><xsl:value-of select="name"/></td>

                    <xsl:choose>
                      <xsl:when test="status='passed'">
                        <td class="table-success"><xsl:value-of select="status"/></td>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:variable name="error" select="error"/>
                        <td class="table-danger" data-toggle="tooltip" data-placement="top" title="{$error}"><xsl:value-of select="status"/></td>
                      </xsl:otherwise>
                    </xsl:choose>
                    <xsl:variable name="var" select="name"/>
                  <td><xsl:value-of select="//time[@testname = $var]/@seconds"/></td>
                    <td>
                      <div class="accordion" id="accordion2">
                          <div class="accordion-group">
                            <div class="accordion-heading">
                              <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseInnerOne">
                              Open/Close Screenshots
                              </a>
                            </div>
                          <div id="collapseInnerOne" class="accordion-body collapse in">
                            <div class="accordion-inner">
                              <img height="300em">
                                <xsl:attribute name="src">
                                <xsl:value-of select="screenshot"/>
                                </xsl:attribute>
                              </img>
                            </div>
                          </div>
                        </div>
                      </div>    
                  </td>
                  </tr>
                  </xsl:for-each>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
        <script type="text/javascript">
        <![CDATA[      
          data = {
          datasets: [{
          data: [
          ]]>
          <xsl:value-of select="count(/testsuite/testcase[status='passed'])"/>,
          <xsl:value-of select="count(/testsuite/testcase[status='failed'])"/>,
          <xsl:value-of select="count(/testsuite/testcase[status='aborted'])"/>
          <![CDATA[ 
          ],
          backgroundColor: ["#03A678","#F22613","#ffff4d "]
          }],
          labels: [
          'Passed',
          'Failed',
          'Aborted'
            ]
          };
          var ctx = document.getElementById('myChart').getContext('2d');
          var chart = new Chart(ctx, {
          type: 'doughnut',
          data: data,
          });
          ]]>
        </script>

        <script type="text/javascript">
        <![CDATA[ 
          var value = ]]>
          <xsl:value-of select="count(/testsuite/testcase) - count(/testsuite/testcase[status='disabled'])"/>
          <![CDATA[ 
          ;
          var max = ]]>
          <xsl:value-of select="count(/testsuite/testcase)"/>
          <![CDATA[ 
          ;
          var bar_ctx = document.getElementById('myChart2');
          var bar_chart = new Chart(bar_ctx, {
          type: 'horizontalBar',
          data: {
          labels: [],
          datasets: [{
          label: 'Tests Run',
          data: [value],
          labels: [],
          backgroundColor: "#03A678",
          }, {
          label: 'Total',
          data: [max - value],
          backgroundColor: "lightgrey",
          }, ]
          },
          options: {
          legend: {
          display: true
          },
          tooltips: {
          enabled: false
          },
          scales: {
          xAxes: [{
          display: true,
          ticks: {mirror: true,
          beginAtZero: true,
          userCallback: function(label, index, labels) {
          if (Math.floor(label) === label) {
          return label;
          }
          },
          },
          stacked: true
          }],
          yAxes: [{
          display: true,
          ticks: {mirror: true},
          stacked: true
          }],
          } // scales
          } // options
          });
          ]]>
        </script>

  <!-- Bootstrap core JavaScript--> 
  <script src="vendor/jquery/jquery.slim.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>

</xsl:template>
</xsl:stylesheet>
