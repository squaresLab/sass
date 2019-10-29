public class Plan1571772839219 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("A") ) {
for (int i = 0; i < 2 ; i++) {
IncreaseTraffic("B");
}

} else {
DecreaseDimmer("A");
}

if ( DecreaseTraffic("A") ) {
DecreaseDimmer("B");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}


}
}
