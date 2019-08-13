// //variable hoisting
// // var x;
// // var x1;
// // var y;
// // var z;
// // var a;
// // var b;
// //var idOne;


// console.log('Hello');

// //primitive types
// //String,number,boolean,null,undefined

// //objects
// //arrays,functions,objects

// var x=10;
// x='ten';
// console.log("TCL: x", x)

// var x1=10.02;
// console.log("TCL: x1", x1)

// var y='java';
// console.log("TCL: y", y)

// var z=true;
// console.log("TCL: z", z)

// var a=null;
// console.log("TCL: a", a)

// // a variable is declared but not assigned a value--undefined

// // Hoisting in Javascript
// console.log('b value is ',b);
// var b='javascript';

// // console.log(c);

// //undefined(a variable is declared but not assigned a value--undefined) vs not defined(not declared)

// //ES6

// //var vs let(block level scope)
// //let  & const

//  const q=10;
// //  q=100;
//  console.log("TCL: q", q)

// //  scope
 
// {
// var e=100;
// }
// console.log("TCL: e", e)

// {
//     let s=100;    
// }

// console.log("TCL: x -> q", q)
// // console.log("TCL: s", s)

// //Truthy & falsy values in js
// console.log('truthy Falsy value')
// console.log(!!true);
// console.log(!!1);
// console.log(!!-1);
// console.log(!![]);
// console.log('java',!!'java');
// console.log(!!0);
// console.log(!!undefined);
// console.log(!!null);
// console.log(!!'');

// //Numbers,strings--pass by value

// console.log('Numbers,Strings')
// console.log(typeof 100);
// console.log(100 instanceof Number);
// console.log(Object.prototype.toString.call(99));
// console.log(Object.prototype.toString.call('Java'));

// function add(a,b){
// a=10;
// b=20;
// console.log(a,b);
// }

// let x2=100;
// let y2=200;

// console.log(x2,y2);

// add(x2,y2);
// console.log(x2,y2);

// //Strings

// var z2='Javascript';
// console.log("TCL: z2", z2.indexOf('s'));

// console.log("TCL: z2", z2.toUpperCase());

// console.log("TCL: z2", z2.length);

// //Objects
// let student={
//     id:'1001',
//     name:'jerry',
//     course:'Java'
// };
// console.log("TCL: student", student)

// console.log(student.name);
// console.log(student['name']);


// let studentCopy=student;
// console.log("TCL: studentCopy", studentCopy)
// studentCopy['id']=1002;
// console.log("TCL: studentCopy", studentCopy)
// console.log("TCL: student", student)



// let studentTwo={
//     id:'1001',
//     name:'jerry',
//     courses:{
//         courseOne:'java',
//         courseTwo:'Javascript'
//     }
// };


// console.log(studentTwo.courses.courseOne);
// console.log(studentTwo['courses']['courseOne']);




// //Copying objects

// //Shallow copy
// // let studentTwoCopy=Object.assign({},studentTwo);//es5 version
// let studentTwoCopy={...studentTwo};//es6 version
// studentTwoCopy.id=1002;
// studentTwoCopy['courses']['courseOne']='Python';
// console.log("TCL: studentTwoCopy", studentTwoCopy)
// console.log("TCL: studentTwo", studentTwo)


// //Deep copy

// let employee={
//     name:'josh',
//     age:30,
//     country:'usa',
//     addresses:{
//         addressOne:'chicago',
//         addressTwo:'Orlando'
//     }
// }


// let empString=JSON.stringify(employee);
// let empCopy=JSON.parse(empString);
// empCopy['name']='Jerry JOSH';
// empCopy['addresses']['addressTwo']='California';
// console.log("TCL: empCopy", empCopy)
// console.log("TCL: employee", employee)


// for(let properties in employee)
// {
//     console.log(properties,employee[properties])    
// }


// console.log(Object.prototype.hasOwnProperty.call(employee,'name'));

// // add properties

// // employee.phone=12345678;
// // console.log(employee);


// function addPropertyEmp(property,value){
//     employee[property]=value;
// }

// addPropertyEmp('phone',123456);
// addPropertyEmp('dept','IT');
// addPropertyEmp('name','Tom');
// addPropertyEmp('education',{bachelors:'BS',masters:'MS'})
// console.log(employee);


// //functions

// //function declaration
// function getData(x,y){
//     console.log('Function called')
//     return x+y;
// }

// const sum=getData(10,20);
// console.log("TCL: sum", sum)


// //function expression
// const data=function(){
//     console.log('function expression called')
// }
// data();

// //scopes

// const p=100;//scope1

// function getTotal(){
//     console.log('p',p);
//     let idOne=200;//scope2 --lexical scope
//     console.log("TCL: getTotal -> idOne", idOne)
    
//     function getTotalCopy(){
//         //scope 3--lexical scope
//         console.log('copy of p',p);
//         console.log('idoNe',idOne);
//         function getTotalCopyTwo(){
//             //scope4
//             console.log('idoNe',idOne);
//             const idTwo=300;
//             console.log("TCL: getTotalCopyTwo -> idTwo", idTwo)            
//         }
//        // console.log("TCL: getTotalCopyTwo -> idTwo oustide getTotalcopyTwo", idTwo)     
//     }
//     getTotalCopy();   
// }

// //console.log(' idOne value outside getTotal',idOne);
// getTotal();


// //closure

// function trainee(course){
//     const courseTaken=course;
//     console.log("TCL: trainee -> courseTaken", courseTaken) 
//     return function(name){
//         return `${name} took ${courseTaken}`;
//     }  
// }


// const JavaCandidates=trainee('Java');
// console.log("TCL: JavaCandidates", JavaCandidates)


// console.log(JavaCandidates("Jerry"));
// console.log(JavaCandidates("Josh"));
// console.log(JavaCandidates("Albert"));

// const AndroidCandidates=trainee('Android');
// console.log(AndroidCandidates('Chelcie'));
// console.log(AndroidCandidates('Gerrit'));

// //template string--es6 feature

// var day="Sunday";
// var fah=90;
// console.log("I just enjoyed this weekend espescially "+day+".Saturday was kind of ok "+fah+" degrees");
// console.log(`I just enjoyed this weekend espescially ${day}.Saturday was kind of ok with ${fah} degrees`)

// //IIFE-Immediately invoked function expression

// const iife=(function(){
//     console.log('iife function')
// })();

// (function(){
//     const ssn='1212uytuy';
//     console.log("TCL: ssn", ssn)    
// })();
// //console.log("TCL: ssn", ssn)  

// //Arrow functions 

// const  getSalary=function(){
//     return 1000;
// }

// const getSalaryCopy=()=>{
//     console.log('arrow function syntax')
// }
// getSalaryCopy();

// //dom-document object model

// //querying dom elements
// // window-->document--->html--->body--->p
// console.log(document.getElementById("para").innerHTML);

// console.log(document.getElementsByClassName("classOne"));
// console.log(document.getElementsByTagName("p"));
// console.log(document.querySelectorAll('.classOne'));

// //callback function
// // Mouse Event-click
// document.getElementById('para').addEventListener('click',()=>{
// console.log('You clicked the para element')
// })

// // 1.Types of Event(eg.,Mouse event)
// // 2.Name 2 events from each category(click,dblclick)


// //event propogation
// //Event bubbling phase,Event capturing phase

// //Bubbling phase-events propogates from child to parent(eg., from li to ul tag)
// document.getElementById('listItem').addEventListener('click',($event)=>{
//     console.log($event)
//     console.log('you clicked '+$event.target.innerHTML);
//     })

// document.getElementById('john').addEventListener('click',($event)=>{
//     //$event.stopPropagation();
//     $event.stopImmediatePropagation();
// })

// document.getElementById('john').addEventListener('click',($event)=>{
//   console.log('you clicked john outside parent element')
// })

// // document.getElementById('listItemTwo').addEventListener('click',()=>{
// //     console.log('You clicked the list element two')
// //     })

    
// var element=document.createElement('p');
// console.log("TCL: element", element)
// var elementText=document.createTextNode('You clicked the button');
// console.log("TCL: elementText", elementText)


// //console.log("TCL: element.appendChild(elementText)", element.appendChild(elementText));

// document.getElementById('btn').addEventListener('click',()=>{
//     document.body.appendChild(element.appendChild(elementText));
// })

//Mock qns
// dom
// events
// event propgation
// event bubbling vs capturing
// e.stopPropogation
// e.stopImmediatePropogation
//e.preventDefault()

// closure
// callback fn
// objects in js
// shallow copy vs deepcopy


// es6 features

// let vs var vs const

// undefined vs not defined

// hoisting

// global scope,lexical scope,block level scope
// what are iife



//Ajax 

//JQUERY---javascript library 

// $(document).ready(()=>{
// console.log('dom loaded')
// $('.clickme').click(()=>{
//     console.log('you clicked the button')
//     $('body').append('<p>You cliked the button</p>');
// })

// $('div:last').click(($event)=>{
// console.log("TCL: event", $event.target)    
// })

// })


//AJAX-Asyncronous Javascript and XML

//Data interchange formats between cleint and server
// JSON and XML
//Javascript object notation
//Extensible markup language

//api='http://api.open-notify.org/astros.json'

//Javascript xhr ajax call
// var xhr=new XMLHttpRequest();

// function requestAstroData(){
//     xhr.open("get",'http://api.open-notify.org/astros.json');

//     xhr.send();
//     xhr.onload=function(){
//         if(xhr.status>=200 && xhr.status<300){
//             console.log(xhr.response);
//             astroData(xhr.response);
//         }
//     }

//     xhr.onerror=function(){
//         this.handleError();
//     }
// }

// //requestAstroData();

// $('button').click(()=>{
//     requestAstroData();
// })



// //Jquery Ajax call

// $.ajax({
//     type:'get',
//     url:'http://api.open-notify.org/astros.json',
//     success:function(response){
//          astroDataCopy(response)
//     },
//     error:function(error){
//         handleError();
//     }
// })


// astroArray=[];
// //Sucess,error callback functions
// function astroData(response){
//     console.log("TCL: astroData -> response", response)
        
//     var response=JSON.parse(response);
//     var astroData=response['people'];
//     astroData.forEach(element => {
//         $('body').append("<p>"+element.name+"</p>");
//     });
//     }
    
//     function astroDataCopy(response){
//         console.log("TCL: astroData -> response", response)
        
//         var astroData=response['people'];
//         astroData.forEach(element => {
//             $('body').append("<p>"+element.name+"</p>");
//             astroArray.push(element.name);
//         });
//         console.log(astroArray);
//         }
//     function handleError(err){
//         console.log(err);
//     }
    
//     $( "#tags" ).autocomplete({
//         source: astroArray
//       });


//Form Validation


$.validator.addMethod('alphanumeric',(value,element)=>{
 return /^\w+$/i.test(value);
},'Aplhabets,numbers only can be used for USername');


$.validator.addMethod('pwdValidator',(value,element)=>{
    return /^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{5,})$/i.test(value);
   },'Minimum 5 characters with one alphabet,one number');

let registerForm=$('#registerform').validate({

    rules:{
        uname:{
            required:true,
            alphanumeric:true
        },
        password:{
            required:true,
            pwdValidator:true
        }
    },
    messages:{
      uname:{
          required:'Username is a mandatory field',
          alphanumeric:'Aplhabets,numbers,underscores only can be used for Username'
      },
      password:{
        required:'Password is a mandatory field',
        pwdValidator:'Minimum 5 characters with one alphabet,one number'
      }
    },

    submitHandler:function(){

        event.preventDefault();
        let uname=$('#uname').val();
       let pwd=$('#password').val();
       

        createUserDta={
            'username':uname,
            'password':pwd
        }

        $.ajax({
            type:'post',
            url:'register/js',
            data:createUserDta,
            contentType:'application/json',
            sucess:function(res){
            console.log("TCL: res", res)
            },
            error:function(res){
            console.log("TCL: res", res)
            }
        })

        $('#registerform')[0].reset();
    }
   
})

const activities = [
    { title: 'Hiking', date: new Date('2019-06-28') },
    { title: 'Shopping', date: new Date('2019-06-10') },
    { title: 'jogging', date: new Date('2018-05-17') },
    { title: 'walking', date: new Date('2020-04-7') },
    { title: 'Trekking', date: new Date('2019-06-22') }
  ]

  console.log(new Date('2019-06-28'));

  const sortedActivities = activities.sort((a, b) => b.date - a.date)
  console.log("TCL: sortedActivities", sortedActivities)
  console.log(sortedActivities[0]['date'].toDateString())