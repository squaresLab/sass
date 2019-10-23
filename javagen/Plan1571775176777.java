public class Plan1571775176777 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
DecreaseDimmer("C");

}

if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}



}
}
