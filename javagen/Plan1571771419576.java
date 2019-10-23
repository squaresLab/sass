public class Plan1571771419576 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

} else {
StartServer("A");
}

DecreaseDimmer("A");

}


}
}
