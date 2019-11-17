<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script>
        function displayDD(a) {
            var c = document.getElementsByClassName("active");
            for (var i = 0; i < c.length; i++) {
                c[i].style.display = "none";
            }
            document.getElementById(a).style.display = "block";
        }
        function updateTextInput(val) {
            document.getElementById('textInput').value=val;
        }
    </script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="#" style="all: initial; float: left; left: 20%;"><img src="../images/compass-icon.png" class="image-icon"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search model" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" style="color: white;">Search</button>
        </form>
    </div>
    <#if user??>
        <button type="button" class="btn btn-link" style="color: white;">${user.username}</button>
    <#else>
        <!-- Modal -->
        <div class="modal fade" id="elegantModalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                in</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signIn">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>

                            <div class="input-group mb-3">
                                <input type="checkbox" name="rememberMe" id="Form-remember1">
                                <label data-error="wrong" data-success="right" for="Form-remember1">&nbsp;&nbsp;Remember
                                    me</label>
                            </div>

                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign
                                    in
                                </button>
                            </div>
                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->


    <#--                SIGN UP FORM-->
        <!-- Modal -->
        <div class="modal fade" id="SignUpForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <!--Content-->
                <div class="modal-content form-elegant">
                    <!--Header-->
                    <div class="modal-header text-center">
                        <h3 class="modal-title w-100 dark-grey-text font-weight-bold my-3" id="myModalLabel"><strong>Sign
                                up</strong></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Body-->
                    <form method="post" action="/signUp">
                        <div class="modal-body mx-4">
                            <!--Body-->
                            <div class="md-form mb-5">
                                <input type="text" name="username" id="Form-email1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-email1">Your nickname</label>
                            </div>

                            <div class="md-form pb-3">
                                <input type="password" name="password" id="Form-pass1" class="form-control validate">
                                <label data-error="wrong" data-success="right" for="Form-pass1">Your password</label>
                            </div>

                            <div class="text-center mb-3">
                                <button type="submit" class="btn blue-gradient btn-block btn-rounded z-depth-1a">Sign
                                    up
                                </button>
                            </div>
                        </div>
                </div>
                </form>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal -->

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#elegantModalForm">Sign
                In</a>
        </div>

        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded" data-toggle="modal" data-target="#SignUpForm">Sign Up</a>
        </div>
    </#if>
</nav>
<div id="container">
    <#--    MENU-->
    <form method="post" action="/test">
        <div id="menu">
            <dl>
                <dt><a href="#">
                        <button id="menuButton" type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-year')">
                            Year
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-year" class="active" style="display: none">
                    <div id="year-container">
                        <div class="slider-control"><label>Year</label>
                            <div>
<#--                                <label for="year">Year</label>-->
<#--                                <input type="range" id="start" name="year-"-->
<#--                                       min="2002" max="2019">-->
<#--                                <input type="range" name="year-" min="2002" max="2019" onchange="updateTextInput(this.value);">-->
<#--                                <input type="text" name="year-" id="textInput" value="">-->
                            </div>
                        </div>
                    </div>
                </dd>
                <#--            Make-->
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-make')">
                            Make
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-make" class="active" style="display: none">
                    <#list makers as maker>
                        <div class="form-check" align="left">
                            <label for="${maker.id}">
                                <input type="checkbox" name="maker_id-${maker.id}" class="form-check-input"
                                       id="${maker.id}">${maker.makerName}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <#--Price-->
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-price')">Price
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-price" class="active" style="display: none">
                    <div class="form-group">
                        <label for="formControlRange">0 to 62300000</label>
                        <input type="range" class="form-control-range" id="formControlRange">
                    </div>
                </dd>
                <#--            Type-->
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-type')">
                            Type
                        </button>
                        <span style="display: none;"></span></a></dt>
                <#list types as type>
                    <div class="form-check" align="left">
                        <label for="type">
                            <input type="checkbox" class="form-check-input" name="type-" id="type">${type.type}
                        </label>
                        <br>
                    </div>
                </#list>
                </dd>
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-engine')">Engine
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-engine" class="active" style="display: none">
                    <div id="year-container">
                        <div class="slider-control">
                            <label>Engine</label>
                        </div>
                </dd>
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-transmission')">Transmission
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-transmission" class="active" style="display: none">
                    <#list transmissions as transmission>
                        <div class="form-check" align="left">
                            <label for="${transmission.id}">
                                <input type="checkbox" class="form-check-input" name="transmission-${transmission.id}"
                                       id="${transmission.id}">${transmission.name}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-perfomance')">Perfomance
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-perfomance" class="active" style="display: none">
                    <div id="year-container">
                        <div class="slider-control">
                            <label>Perfomance</label>
                        </div>
                </dd>
                <dt><a href="#">
                        <button type="button" class="btn btn-secondary btn-lg btn-block"
                                onclick="displayDD('active-capacity')">Capacity
                        </button>
                        <span style="display: none;"></span></a></dt>
                <dd id="active-capacity" class="active" style="display: none">
                    <#list capacities as cap>
                        <div class="form-check" align="left">
                            <label for="${cap}">
                                <input type="checkbox" class="form-check-input" name="capacity-${cap}"
                                       id="${cap}">${cap}
                            </label>
                            <br>
                        </div>
                    </#list>
                </dd>
            </dl>
            <button type="submit">Submit</button>
        </div>
    </form>

    <div id="content">
        <#if cars??>
            <div id="top-left" style="float: left; height: 50%; width: 50%"><#if cars[0]??>
                <a href="/cars/${cars[0].id}">
                <#--                <img style="width: 80%" height="300px" src="../images/cars/Renault_Duster.png">-->

                    <img style="width: 80%" height="300px" src="../images/cars/${cars[0].imagePath}">

                    </a></#if>
            </div>
            <div id="top-right" style="float: right; height: 50%; width: 50%"><#if cars[1]??>
                <a href="/cars/${cars[1].id}">
                <#--                <img style="width: 80%" height="300px" src="../images/cars/Kia_Rio.png">-->

                    <img style="width: 80%" height="300px" src="../images/cars/${cars[1].imagePath}">
                    </a></#if>
            </div>
            <div id="bottom-left" style="float: left; height: 50%; width: 50%"><#if cars[2]??>
                <a href="/cars/${cars[2].id}">

                <#--                <img style="width: 80%" height="300px" src="../images/cars/Toyota_Camry.png">-->
                    <img style="width: 80%" height="300px" src="../images/cars/${cars[2].imagePath}">
                    </a></#if>
            </div>
            <div id="bottom-right" style="float: right; height: 50%; width: 50%"><#if cars[3]??>
                    <a href="/cars/${cars[3].id}">
                        <#--                <img style="width: 80%" height="300px" src="../images/cars/Chevrolet_Niva.png">-->
                        <img style="width: 80%" height="300px" src="../images/cars/${cars[3].imagePath}">
                    </a>
                </#if>
            </div>
        </#if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>