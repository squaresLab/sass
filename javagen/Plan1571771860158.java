public class Plan1571771860158 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
DecreaseDimmer("A");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

if ( StartServer("A") ) {
IncreaseTraffic("B");
} else {
if ( StartServer("A") ) {
IncreaseTraffic("B");
} else {
DecreaseDimmer("A");
}

}


}
}
