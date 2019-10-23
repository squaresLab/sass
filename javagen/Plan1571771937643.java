public class Plan1571771937643 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}

}

}


}
}
