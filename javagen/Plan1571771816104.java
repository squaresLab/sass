public class Plan1571771816104 extends Plan { 
public static void main(String[] args) { 
IncreaseTraffic("B");
StartServer("C");
StartServer("A");

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
IncreaseTraffic("B");
}

if ( StartServer("C") ) {
StartServer("A");
} else {
IncreaseDimmer("A");
}


}



StartServer("B");

StartServer("C");

}
}
