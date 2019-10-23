public class Plan1571775091185 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {

} else {
StartServer("A");
}

}

DecreaseDimmer("C");

StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


DecreaseDimmer("A");


}
}
