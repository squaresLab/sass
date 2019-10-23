public class Plan1571775600402 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
DecreaseDimmer("C");
} else {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

}

}

}
}
