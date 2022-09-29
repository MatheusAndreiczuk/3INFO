#include "colors.inc"  
#include "textures.inc"

camera{
    location <40,30,-100>
    look_at <0,0,0>
}

light_source{
    <0, 100, -100>
    color White
}   

sky_sphere{
    pigment {
        gradient y
            color_map {
                [0 color Red]
                [0.3 color Blue]
            }
    }   
}      

plane {
    <0, 1, 0>, 0
    pigment {
        color Green
    }
} 
 

  
 
  
difference{


difference {
    box { 
        <-10, 0, 0>, 
        <15, 15, 30> 
        pigment{ 
            color Yellow 
        } 
    }   
          
          
    box { 
        <-9, 2, 1>, 
        <14, 16, 29> 
       pigment {
            color Yellow
       }
 
    }
    
     box {
        <-6, 0.01, -1>,
        <1, 10, 2>  
                 texture{ // ----------------------------------------   
            pigment{ wood turbulence 0.02 octaves 4 lambda 3
                     scale 0.175  rotate <2, 3, 0> 
                     color_map {
                        [0.0 color rgb <1.00, 0.88, 0.54>]
                        [0.1 color rgb <1.00, 0.80, 0.54>]
                        [0.5 color rgb <0.70, 0.42, 0.23>]
                        [0.7 color rgb <0.70, 0.42, 0.23>]
                        [1.0 color rgb <1.00, 0.88, 0.54>]
                     }// end of color_map
                   } // end of pigment
            // normal { agate 0.5 scale 0.25} 
            finish { phong 1 } 
            rotate <0,0, 0>  scale 1  translate <0,0,0>
        } // end of texture ---------------------------------
    }
    
    box {
        <7, 5, -1>,
        <12, 10, 2>  
               texture{ DMFWood3    
                normal { wood 0.5 scale 0.05 rotate<0,0,0> }
                finish { phong 1 } 
                rotate<0,0,0> scale 1  translate<0,0,0>
              } // end of texture 

    }
  }  
    
           
} 
   
   
   difference {
box {            
        <-12, 13, 1>
        <17, 37, 29>
        pigment {
            color Red
        } 
        rotate <0, 0, 45>   
        translate <17, -5, 0>
    } 
      
      
      box { 
        <-19, 0, 0>, 
        <19, 15, 30> 
        pigment{ 
            color Yellow 
        } 
    }  
    }

       
   
    
   
    
