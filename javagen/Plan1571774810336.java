public class Plan1571774810336 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
DecreaseDimmer("A");
} else {
if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

}

}

}

}
}
