public class Plan1571771621269 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("C");

}

if ( StartServer("A") ) {
StartServer("B");
} else {
DecreaseDimmer("A");
DecreaseDimmer("A");

}


for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}


}


}
}
