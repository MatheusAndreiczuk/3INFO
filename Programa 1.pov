#include "colors.inc"
#include "woods.inc" 
#include "metals.inc"
#include "textures.inc"
#include "glass.inc"



camera{
    location <0,5,-5>
    look_at <0,0,0>
}

light_source{
    <0, 8, -5>
    color White
} 

plane {
    <0, 1, 0>, 0
    pigment {
        color Gray
    }

}  

cylinder{
    <0, 0, 0>
    <0, 0.1, 0>, 0.5
             texture { T_Silver_5A
                   //normal { bumps 0.5 scale 0.15} 
                   finish { phong 1}
                 } // end of texture 

}  

cylinder{
    <0, 2.05, 0>
    <0, 0, 0>, 0.1
             texture { T_Silver_5A
                   //normal { bumps 0.5 scale 0.15} 
                   finish { phong 1}
                 } // end of texture 

}   

cylinder{
    <0, 2, 0>
    <0, 2.1, 0>, 1
           texture{ DMFDarkOak
                normal { wood 0.5 scale 0.3 turbulence 0.1}
                finish { phong 1 } 
                rotate<60,0,45> scale 0.25
              } // end of texture 

} 

difference{

cylinder {
    <0, 2.11, 0>
    <0, 2.35, 0>, 0.12
             material{ texture { NBglass } // end of texture 
                   interior{ I_Glass } // end of interior
                 } // end of material -------------------

}

cylinder {
    <0, 2.15, 0>
    <0, 2.3, 0>, 0.9
             material{ texture { NBglass } // end of texture 
                   interior{ I_Glass } // end of interior
                 } // end of material -------------------

} 
}


cylinder {
    <0, 2.15, 0>
    <0, 2.25, 0>, 0.08
             material{ texture { NBwinebottle } // end of texture 
                   interior{ I_Glass } // end of interior
                 } // end of material -------------------

}

box {
    <0.3, 2.15, 0>
    <0.5, 2.15, 0.4>
    pigment {
        image_map { png "classic.jpg" }  
        rotate <90, 0, 0>
        scale <0.2, 1, 0.4>
          translate <-0.3, 0, 0>
   }
} 
  



 
 
