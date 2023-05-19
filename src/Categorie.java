public class Categorie {
     String type,couleur;
     
     public Categorie(String type, String couleur) {
          this.type = type;
          this.couleur = couleur;
          }
         
     public String getType() {
            return type;
          }
         
     public void setType(String type) {
          this.type = type;
          }
         
     public String getCouleur() {
          return couleur;
          }
         
     public void setCouleur(String couleur) {
          this.couleur = couleur;
          }
          
          public boolean equals(Categorie cat)
          {
               if(type.equals(cat.getType()))
                    return true;
               return false;
          }  
     }